public class Publisher extends Component {
    private Topic topic;

    // Constructor: registra nombre + topico en el broker
    public Publisher(String name, Broker broker, String topicName) {
        super(name, topicName);
        // Esto crea el Topic y lo agrega al broker
        this.topic = broker.createTopic(topicName);
    }

    // Publica un mensaje (o coordenadas) en ese topico
    public void publishNewEvent(String message) {
        topic.notify(message);
    }
}
