import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Simulador {
    private Broker broker;
    private List<Publisher> publishers;
    private List<Subscriber> subscribers;
    private Map<String, Subscriber> subscriberMap;
    
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: java Simulador <configFile.txt>");
            System.exit(-1);
        }
        
        String configFile = args[0];
        Simulador simulador = new Simulador();
        simulador.setupSimulator(configFile);
        simulador.runSimulation();
    }
        
    public void setupSimulator(String configFile) {

        broker = new Broker();
        publishers = new ArrayList<>();
        subscribers = new ArrayList<>();
        subscriberMap = new HashMap<>();
        
        // Primera lectura: SOLO PUBLICADORES
        try {
            Scanner in = new Scanner(new File(configFile));
            
            while (in.hasNext()) {
                String tipo = in.next();
                if (tipo.equals("publicador")) {
                    String name = in.next();
                    String topicName = in.next();
                    // Crear y guardar el publisher
                    Publisher pub = new Publisher(name, broker, topicName);
                    publishers.add(pub);
                } else {
                    // Saltamos los datos de suscriptores
                    in.next(); // subType
                    in.next(); // name
                    in.next(); // topic
                    in.next(); // fileOut
                }
            }
            in.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.exit(-1);
        }
        
        // Segunda lectura: SOLO SUSCRIPTORES
        try {
            Scanner conf = new Scanner(new File(configFile));
            
            while (conf.hasNext()) {
                String tipo = conf.next();
                if (tipo.equals("suscriptor")) {
                    String subType = conf.next();      // "Seguidor", "Registrador" o "Monitor"
                    String name    = conf.next();
                    String topic   = conf.next();
                    String fileOut = conf.next();
                    
                    // Comprobamos si ya existe un suscriptor con este nombre
                    Subscriber sub = subscriberMap.get(name);
                    
                    if (sub == null) {
                        // No existe, creamos uno nuevo
                        try {
                            PrintStream out = new PrintStream(fileOut);
                            
                            if (subType.equals("Seguidor")) {
                                sub = new Follower(name, topic, out);
                            } else if (subType.equals("Registrador")) {
                                sub = new Recorder(name, topic, out);
                            } else if (subType.equals("Monitor")) {
                                sub = new Monitor(name, topic, out);
                            }
                            
                            if (sub != null && broker.subscribe(sub)) {
                                subscribers.add(sub);
                                subscriberMap.put(name, sub); // Guardamos para referencia futura
                            } else {
                                System.out.println("Error al suscribir " + name + " al tópico " + topic);
                            }
                        } catch (FileNotFoundException e) {
                            System.out.println("No se puede crear archivo: " + fileOut);
                            System.exit(-1);
                        }
                    } else {
                        // Ya existe un suscriptor con este nombre, lo suscribimos al nuevo tópico
                        sub.addTopic(topic);
                        if (broker.subscribeToTopic(sub, topic)) {
                            // Éxito al suscribir al nuevo tópico
                        } else {
                            System.out.println("Error al suscribir " + name + " al tópico adicional " + topic);
                        }
                    }
                } else {
                    // Saltamos los datos de publicadores
                    conf.next(); // name
                    conf.next(); // topicName
                }
            }
            conf.close();
        } catch (FileNotFoundException e) {
            System.out.println("Archivo de configuración no encontrado: " + configFile);
            System.exit(-1);
        }
    }

    public void runSimulation() {

        Scanner inputEvent = new Scanner(System.in);
        System.out.println("STREAMERS DISPONIBLES: ");
        for (Publisher p : publishers) {
            System.out.println(p.getName());
        }
        System.out.println("Simulador listo. Formato de evento:");
        System.out.println("<PublisherName> <mensaje o coordenadas>");
        
        while (inputEvent.hasNextLine()) {
            String line = inputEvent.nextLine().trim();
            if (line.isEmpty()) continue;
            
            Scanner lineScanner = new Scanner(line);
            String pubName = lineScanner.next();
            
            // Buscar el publisher por nombre
            Publisher publicador = null;
            for (Publisher p : publishers) {    
                if (p.getName().equals(pubName)) {
                    publicador = p;
                    break;
                }
            }
            
            if (publicador == null) {
                System.out.println("Unknown Publisher");
                lineScanner.close();
                continue;
            }
            
            // Reconstruir el resto del mensaje
            StringBuilder message = new StringBuilder();
            while (lineScanner.hasNext()) {
                message.append(lineScanner.next()).append(" ");
            }

            publicador.publishNewEvent(message.toString().trim());
            lineScanner.close();
        }
        inputEvent.close();
    }
}
