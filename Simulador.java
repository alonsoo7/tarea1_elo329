import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Simulador {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: java Simulador <configFile.txt>");
            System.exit(-1);
        }
        // 1. Abrir el archivo de configuracion
        Scanner conf = null;
        try {
            conf = new Scanner(new File(args[0]));
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + args[0]);
            System.exit(-1);
        }
        
        // 2. Crear el broker y listas para publishers/subscribers
        Broker broker = new Broker();
        List<Publisher> publishers = new ArrayList<>();
        List<Subscriber> subscribers = new ArrayList<>();
        
        // 3. Leer cada linea de config
        while (conf.hasNext()) {
            String tipo = conf.next();  // "publicador" o "suscriptor"
            if (tipo.equals("publicador")) {
                String name = conf.next();
                String topicName = conf.next();
                // Crear el topico en el broker (si no existe)
                //broker.createTopic(topicName);
                // Crear y guardar el publisher
                Publisher pub = new Publisher(name, broker, topicName);
                publishers.add(pub);
            }
            else if (tipo.equals("suscriptor")) {
                String subType = conf.next();      // "Seguidor", "Registrador" o "Monitor"
                String name    = conf.next();
                String topic   = conf.next();
                String fileOut = conf.next();
                try {
                    PrintStream out = new PrintStream(fileOut);
                    Subscriber sub = null;
                    if (subType.equals("Seguidor")) {
                        sub = new Follower(name, topic, out);
                    } else if (subType.equals("Registrador")) {
                        sub = new Recorder(name, topic, out);
                    } else if (subType.equals("Monitor")) {
                        sub = new Monitor(name, topic, out);
                    }
                    if (sub != null && broker.subscribe(sub)) {
                        subscribers.add(sub);
                    } else {
                        System.out.println("Error al suscribir " + name + " al topico " + topic);
                    }
                } catch (FileNotFoundException e) {
                    System.out.println("No se puede crear archivo: " + fileOut);
                    System.exit(-1);
                }
            }
            else {
                // linea malformada
                System.out.println("Linea de config invalida: " + tipo);
            }
        }
        conf.close();
        
        // 4. Correr la simulacion por teclado
        Scanner input = new Scanner(System.in);
        System.out.println("Simulador listo. Formato de evento:");
        System.out.println("<PublisherName> <mensaje o coordenadas>");
        
        while (input.hasNextLine()) {
            String line = input.nextLine().trim();
            if (line.isEmpty()) continue;
            Scanner scLine = new Scanner(line);
            String pubName = scLine.next();
            // Buscar el publisher por nombre
            Publisher quien = null;
            for (Publisher p : publishers) {
                if (p.getName().equals(pubName)) {
                    quien = p;
                    break;
                }
            }
            if (quien == null) {
                System.out.println("Unknown Publisher");
                scLine.close();
                continue;
            }
            // Reconstruir el resto del mensaje
            StringBuilder msg = new StringBuilder();
            while (scLine.hasNext()) {
                msg.append(scLine.next()).append(" ");
            }
            scLine.close();
            // Publicar
            quien.publishNewEvent(msg.toString().trim());
        }
        input.close();
    }
}
