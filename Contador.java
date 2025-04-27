import java.io.PrintStream;

public class Contador extends Subscriber {
    private int count;
    private long startTime;
    private PrintStream out;

    //contructor
    public Contador(String name, String topicName, PrintStream out, long startTime) {
        super(name, topicName);
        this.out = out;
        this.startTime = startTime;
        this.count = 0;
    }

    @Override
    public void update(String topic, String message) {
        // 1) Aumenta el contador
        count++;
        // 2) Calcula el tiempo transcurrido
        long elapsedSec = (System.currentTimeMillis() - startTime) / 1000;
        // 3) Meter al CSV el par <segundos>,<conteo>
        out.println(elapsedSec + "," + count);
        out.flush();
        // 4) Mostrar en consola
        System.out.println(getName() + " ha recibido " + count +" mensajes en los topicos que se suscribio hasta el segundo " + elapsedSec + ".");
    }                          
                            
}
