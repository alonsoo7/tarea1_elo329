public class Publisher extends Component {

   protected Publisher() {} // to ban calls to this constructor
   
   public Publisher(String name, Broker broker, String topicName) {
      super(name, topicName);
      this.topic = broker.createTopic(topicName);
   }

   protected void publishNewEvent(String message) {
      try {
          topic.notify(message);
      } catch (Exception e) {
          System.err.println("Error al publicar mensaje en topic: " + e.getMessage());
      }
  }

   private Topic topic;

}