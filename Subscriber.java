public abstract class Subscriber extends Component {

   //Constructor protegido 
      protected Subscriber() {
         super();
      }  
      
     //Constructor principal  
      public Subscriber(String name, String topicName) {
         super(name, topicName);
      }
   //Metodo que se invoca al recibir un nuevo mensaje.
      public abstract void update(String message, String fromTopic);
   }