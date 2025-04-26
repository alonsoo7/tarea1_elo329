import java.util.ArrayList;

public class Topic {
    private String topicName;
    private ArrayList<Subscriber> subscribers;

    // Constructor
    public Topic(String topicName) {
        this.topicName = topicName;
        this.subscribers = new ArrayList<>();
    }

    // Agrega un suscriptor
    public void subscribe(Subscriber sub) {
        subscribers.add(sub);
    }

    // Notifica a todos los suscriptores
    public void notify(String message, String fromTopic) {
        for (Subscriber sub : subscribers) {
            sub.update(message, fromTopic);
        }
    }

    // Notifica a todos los suscriptores (para compatibilidad)
    public void notify(String message) {
        notify(message, this.topicName);
    }

    // Comparar nombres
    public boolean hasThisName(String name) {
        return this.topicName.equals(name);
    }
}
