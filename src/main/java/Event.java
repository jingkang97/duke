public class Event extends Task{

    public Event(String description){
        super(description);
    }

    public String toString(){
        return "E" + super.toString();
    }

}