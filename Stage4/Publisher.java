public class Publisher extends Component {

   protected Publisher() {} //Evitar que se llame a este constructor 
   
   //Si el topico no existia, se crea; si ya existe, se reutiliza.
   public Publisher(String name, Broker broker, String topicName) {
      super(name, topicName);
      if (broker.findTopic(topicName) == null) {
         this.topic = broker.createTopic(topicName);
      } else {
         this.topic = broker.findTopic(topicName);
      }
   }

   @Override
   public void addTopic(String topic) {
        // solo para evitar que se hagan mas topicos. no hace nada
   }

   //Informa error si el mensaje no es valido al publica el topico 
   protected void publishNewEvent(String message) {
      try {
        topic.notify(message, topicName);
      } catch (Exception e) {
        System.err.println("Error al publicar mensaje en topic. Asegurate que son coordenadas validas (x,y) o que el mensaje es correcto: " + e.getMessage());
      }
  }

   private Topic topic;

}