import java.util.ArrayList;
import java.util.List;

public class Component {

   protected String name;
   protected String topicName; // Para compatibilidad con versiones anteriores
   protected List<String> topicNames;
   
   protected Component (){
      topicNames = new ArrayList<>();
   }
   
   // Construye un componente con nombre y topico inicial.
   public Component(String componentName, String _topicName){
      name = componentName;
      topicName = _topicName;
      topicNames = new ArrayList<>();
      topicNames.add(_topicName);
   }
   
   //Devuelve el nombre del componente
   public String getName(){
      return name;
   }
   
   //Devuelve el topico principal
   public String getTopicName(){
      return topicName;
   }
   
   //Devuelve la lista completa de topicos suscritos
   public List<String> getTopicNames() {
      return topicNames;
   }
   //Añade un nuevo topico a la lista, evitando duplicados.
   public void addTopic(String topic) {
      if (!topicNames.contains(topic)) {
         topicNames.add(topic);
      }
   }
}