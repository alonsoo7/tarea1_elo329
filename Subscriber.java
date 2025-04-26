
public abstract class Subscriber extends Component {

   protected Subscriber() {
      super();
   }  
   
   public Subscriber(String name, String topicName) {
      super(name, topicName);
   }

   public abstract void update(String message, String fromTopic);
}