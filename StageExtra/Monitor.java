import java.io.PrintStream;
import java.util.Scanner;

public class Monitor extends Subscriber {

    private PrintStream out;

    // Constructor
    public Monitor(String name, String topicName, PrintStream out) {
        super(name, topicName);
        this.out = out;
    }

    @Override
    public void update(String message, String fromTopic) {
        //1.Extraer coordenadas
        Scanner sc = new Scanner(message);
        if (!sc.hasNextInt()) {
            sc.close();
            return;  // no es un mensaje valido de coordenadas
        }
        int x = sc.nextInt();
        if (!sc.hasNextInt()) {
            sc.close();
            return;
        }
        int y = sc.nextInt();
        sc.close();

        // 2. Calcular distancia al origen
        double distancia = Math.sqrt(x*x + y*y);

        // 3. Si supera 500, escribir en el archivo
        if (distancia > 500 && 500 >= y && 500 >= x && 0 <= y && 0 <= x) {
            // getName() viene de Component
            out.println(getName() + "," + fromTopic + "," + x + "," + y);
        }
    }
}

