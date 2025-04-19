import java.util.Scanner;

public class T1Stage2 {

    public static void main(String[] args) {

        Broker broker = new Broker();

        Publisher gps = new Publisher("GPS", broker, "ubicacion");
        Recorder recorder = new Recorder("Recorder1", "ubicacion");

        boolean success = broker.subscribe(recorder);
        if (!success) {
            System.err.println("Failed to subscribe recorder to topic 'ubicacion'");
            return;
        }

        Scanner scanner = new Scanner(System.in);
        System.out.println("Write messages in CSV-format (f.eks. tid,lat,long):");

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (line.isEmpty()) break;

            gps.publishNewEvent(line);  
        }

        System.out.println("Recording ended.");
        scanner.close();
    }
}