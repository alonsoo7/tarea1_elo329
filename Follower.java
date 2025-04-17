import java.io.PrintStream;

public class Follower extends Subscriber {

   public Follower(String name, String topicName, PrintStream out) {
      super(name, topicName);
      this.out = out;
      out.println("Formato: (Nombre) (Topico) (Mensaje)");
   }
   public void update(String message) {
      out.println(name + " " + topicName + " " + message);
      out.flush();
   }
   
   private PrintStream out;
} 