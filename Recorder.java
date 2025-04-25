import java.io.PrintStream;
import java.util.Scanner;

public class Recorder extends Subscriber {
    private PrintStream out;

    // Constructor: recibe nombre, topico y flujo de salida
    public Recorder(String name, String topicName, PrintStream out) {
        super(name, topicName);
        this.out = out;
    }

    @Override
    public void update(String message) {
        // x y y
        Scanner sc = new Scanner(message);
        if (!sc.hasNextInt()) { sc.close(); return; }
        int x = sc.nextInt();
        if (!sc.hasNextInt()) { sc.close(); return; }
        int y = sc.nextInt();
        sc.close();

        // Guardamos en formato CSV
        out.println(getName() + "," + getTopicName() + "," + x + "," + y);
        out.flush();
    }
}
