import java.io.PrintStream;

public class Follower extends Subscriber {

   //constructor
   public Follower(String name, String topicName, PrintStream out) {
      super(name, topicName);
      this.out = out;
   }

   
   @Override
   public void update(String message, String fromTopic) {
      // 1. Formatear y escribir notificacion
      out.println(name + " " + fromTopic + " " + message);
     // 2. Forzar volcado al archivo
      out.flush();
   }
   
   private PrintStream out;
} 
