import java.io.PrintStream;
import java.util.Scanner;


 //Recorder graba cada posicion recibida en formato CSV.

public class Recorder extends Subscriber {
    private PrintStream out;
   protected Recorder() {}  //Evitar llamadas a este constructor 

   //Constructor principal 
   public Recorder(String name, String topicName, PrintStream out) {
      super(name, topicName);
      this.out = out;
   }

   
   @Override
   public void update(String message, String fromTopic) {
      //Extrae coordenadas x e y
      Scanner in = new Scanner(message);
      out.println(getName()+","+fromTopic+","+in.nextInt()+","+in.nextInt()); 
   }
   
} 