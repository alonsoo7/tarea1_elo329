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
        
      Scanner in = null;
      try {
         in = new Scanner(new File(args[0]));
      } catch (FileNotFoundException e) {
         e.printStackTrace();
         System.out.println("File: " + args[0]);
         System.exit(-1);
      }
      T1Stage3 stage = new T1Stage3();
      stage.setupSimulator(in);
      stage.runSimulation();
   }

   public void setupSimulator(Scanner in) {  // create main objects from configuration file
      broker = new Broker();
      String component;
      String componentName;
      String topicName;
      String fileName;
      
      // Configurar el streamer
      component = in.next();  
      componentName = in.next();
      topicName = in.next(); 
      if(component.equals("publicador")) { 
         streamer = new Publisher(componentName, broker, topicName);
         streamerName = componentName;
      } else {
         System.out.println("Error: El primer componente debe ser un publicador");
      }
      
      // extraer informacion del primer seguidor
      component = in.next(); 
      String componentType = in.next(); 
      componentName = in.next();
      topicName = in.next();
      fileName = in.next();

      try {
         if (component.equals("suscriptor")) {
            if(componentType.equals("Seguidor")) {
               Follower follower = new Follower(componentName, topicName, new PrintStream(fileName));
               broker.subscribe(follower);
            } else {
               System.out.println("Error: El segundo componente debe ser un seguidor en Stage3");
            }
         }
      } catch (FileNotFoundException e) {
         e.printStackTrace();
         System.out.println("File: " + fileName);
         System.exit(-1);
      }
      
      // extraer informacion del segundo seguidor
      component = in.next(); 
      String componentType2 = in.next(); 
      componentName = in.next();
      topicName = in.next();
      fileName = in.next();
      
      try {
         if (component.equals("suscriptor")) {
            if(componentType2.equals("Seguidor")) {
               Follower follower = new Follower(componentName, topicName, new PrintStream(fileName));
               broker.subscribe(follower);
            } else {
               System.out.println("Error: El tercer componente debe ser un seguidor en Stage3");
            }
         }
      } catch (FileNotFoundException e) {
         e.printStackTrace();
         System.out.println("File: " + fileName);
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
