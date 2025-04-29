import java.util.ArrayList;

public class Broker {

   @SuppressWarnings("FieldMayBeFinal")
   private ArrayList<Topic> topics;

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
      String topicName = sub.getTopicName();
      Topic topic;

      if ((topic=findTopic(topicName))!=null) { // si el topico existe suscribe
         topic.subscribe(sub);
         return true;
      } else 
         return false; // topic does not exist.
   }

   private Topic findTopic(String topicName) {
      for (Topic topic:topics) {
         if (topic.hasThisName(topicName)) {
            return topic;
         }
      }
      return null;
   }
}