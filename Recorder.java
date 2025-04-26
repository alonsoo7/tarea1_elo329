import java.io.PrintStream;
import java.util.Scanner;

public class Recorder extends Subscriber {
    private PrintStream out;
   protected Recorder() {}  // to ban calls to this constructor.
   public Recorder(String name, String topicName, PrintStream out) {
      super(name, topicName);
      this.out = out;
   }
   @Override
   public void update(String message, String fromTopic) {
      Scanner in = new Scanner(message);
      out.println(getName()+","+fromTopic+","+in.nextInt()+","+in.nextInt());
   }
   
} 