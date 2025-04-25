import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;

public class T1Stage3 {
   public static void main (String args[]) {
      if (args.length != 1) {
            System.out.println("Usage: java T1Stage3 <configurationFile.txt>");
            System.exit(-1);
        }
        
      String configFile = args[0];
      T1Stage3 stage = new T1Stage3();
      stage.setupSimulator(configFile);
      stage.runSimulation();
   }

   public void setupSimulator(String configFile) {  // create main objects from configuration file
      broker = new Broker();
      Scanner in = null;
      String publisherTopic = ""; // Guardar el tópico del publicador, para usarlo con los suscriptores
      
      // Primera lectura: SOLO EL PUBLICADOR
      try {
         in = new Scanner(new File(configFile));
         boolean publicadorEncontrado = false;
         
         while (in.hasNext() && !publicadorEncontrado) {
            String component = in.next();
            if (component.equals("publicador")) {
               String componentName = in.next();
               String topicName = in.next();
               publisherTopic = topicName; // Guardar el tópico para usarlo con los suscriptores
               streamer = new Publisher(componentName, broker, topicName);
               streamerName = componentName;
               publicadorEncontrado = true;
            } else {
               // se saltan todos los datos de suscriptores
               in.next(); // componentType
               in.next(); // componentName
               in.next(); // topicName
               in.next(); // fileName
            }
         }
         if(!publicadorEncontrado){
            System.out.println("Error: No hay publicador en el archivo.");
            System.exit(-1);
         }
         in.close();
      } catch (FileNotFoundException e) {
         e.printStackTrace();
         System.exit(-1);
      }
      
      // Segunda lectura: buscar los dos suscriptores
      try {
         in = new Scanner(new File(configFile));
         int subscribersFound = 0;
         
         while (in.hasNext() && subscribersFound < 2) {
            String component = in.next();
            if (component.equals("suscriptor")) {
               String componentType = in.next();
               String componentName = in.next();
               String topicName = in.next();
               String fileName = in.next();
               
               if (componentType.equals("Seguidor")) {
                  // Verificar que el tópico coincida con el del publicador
                  if (topicName.equals(publisherTopic)) {
                     Follower follower = new Follower(componentName, topicName, new PrintStream(fileName));
                     broker.subscribe(follower);
                     subscribersFound++;
                  } else {
                     System.out.println("Error: Los tópicos no coinciden.");
                     System.exit(-1);
                  }
               } else {
                  System.out.println("Error: El suscriptor debe ser de tipo Seguidor en Stage3");
               }
            } else {
               // Saltamos los datos del publicador
               in.next(); // componentName
               in.next(); // topicName
            }
         }
         in.close();
      } catch (FileNotFoundException e) {
         e.printStackTrace();
         System.exit(-1);
      }
   }
   
   public void runSimulation() {
      System.out.println("STREAMER DISPONIBLE: " + streamerName);
      System.out.println("FORMATO DE USO: \"(streamer) (mensaje string)\"");
      
      Scanner inputEvent = new Scanner(System.in);
      while (inputEvent.hasNextLine()) {
         String line = inputEvent.nextLine();
         Scanner lineScanner = new Scanner(line);
         
         if (lineScanner.hasNext()) {
            String publisherName = lineScanner.next();
            
            if (publisherName.equals(streamerName)) {
               StringBuilder message = new StringBuilder();
               while (lineScanner.hasNext()) {
                  message.append(lineScanner.next()).append(" ");
               }
               streamer.publishNewEvent(message.toString());
            } else {
               System.out.println("Unknown Publisher");
            }
         }
         
         lineScanner.close();
      }
      inputEvent.close();
   }
   
   private Publisher streamer;
   private String streamerName;
   private Broker broker;
}
