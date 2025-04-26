import java.util.ArrayList;
import java.util.List;

public class Component {

   protected String name;
   protected String topicName; // Para compatibilidad con versiones anteriores
   protected List<String> topicNames;
   
   protected Component (){
      topicNames = new ArrayList<>();
   }
   
   public Component(String componentName, String _topicName){
      name = componentName;
      topicName = _topicName;
      topicNames = new ArrayList<>();
      topicNames.add(_topicName);
   }
   
   public String getName(){
      return name;
   }
   
   public String getTopicName(){
      return topicName;
   }
   
   public List<String> getTopicNames() {
      return topicNames;
   }
   
   public void addTopic(String topic) {
      if (!topicNames.contains(topic)) {
         topicNames.add(topic);
      }
   }
}