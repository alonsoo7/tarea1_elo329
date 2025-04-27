import java.util.ArrayList;

public class Broker {

   @SuppressWarnings("FieldMayBeFinal")
   private ArrayList<Topic> topics;

   //Inicializa el broker con lista de topics vacia.
   @SuppressWarnings("Convert2Diamond")
   public Broker() {
      topics = new ArrayList<Topic>();
   }

   public Topic createTopic(String topicName){ // aqui se crea el topic al inicializar un publisher
      Topic topic = new Topic(topicName);
      topics.add(topic);
      return topic;
   }

   
   public boolean subscribe(Subscriber sub){
      // Suscribir al topico principal (para compatibilidad)
      String topicName = sub.getTopicName();
      return subscribeToTopic(sub, topicName);
   }
   
   public boolean subscribeToTopic(Subscriber sub, String topicName){
      Topic topic = findTopic(topicName);
      
      if (topic != null) { // si el topico existe, suscribe
         topic.subscribe(sub); 
         return true;
      } else 
         return false; // topico no encontrado
   }

   //Busca un topic por nombre
   protected Topic findTopic(String topicName) {
      for (Topic topic:topics) {
         if (topic.hasThisName(topicName)) {
            return topic;
         }
      }
      return null;
   }
}