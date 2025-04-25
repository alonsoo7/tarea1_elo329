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
        // Guardamos en formato CSV: nombre,topic,mensaje
        out.println(getName() + "," + getTopicName() + "," + message);
        out.flush();
    }
}