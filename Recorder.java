import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Recorder extends Subscriber {

    private PrintWriter writer;

    public Recorder(String name, String topicName) {
        super(name, topicName);
        try {
            writer = new PrintWriter(new FileWriter("trayectoria.txt", true));
        } catch (IOException e) {
            System.err.println("Error opening trayectoria.txt: " + e.getMessage());
        }
    }

    @Override
    public void update(String message) {
        writer.println(message);  
        writer.flush();
    }
}
