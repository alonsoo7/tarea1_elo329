import java.io.PrintStream;

public class Follower extends Subscriber {
    private PrintStream out;

    // Constructor: recibe nombre, topico y flujo de salida
    public Follower(String name, String topicName, PrintStream out) {
        super(name, topicName);
        this.out = out;
    }

    @Override
    public void update(String message) {
        // Al llegar un mensaje, lo guardamos con formato CSV simple
        out.println(getName() + "," + getTopicName() + "," + message);
        out.flush();  // fuerza el volcado inmediato
    }
}
