public class Component {

   protected String name;
   protected String topicName;
   
   protected Component (){}  // to ban creation of publisher or subscriber without name.
   public Component(String componentName, String _topicName){
      name = componentName;
      topicName = _topicName;
   }
   public String getName(){
      return name;
   }
   public String getTopicName(){
      return topicName;
   }
   
}