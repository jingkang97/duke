public class Event extends Task{

    protected String at;

    public Event(String description, String at)throws DukeException{
        super(description);
        if(description.equals("event ") || description.equals("event")){
            throw new DukeException("______________________________________________________________________________\n ☹ OOPS!!! The description of an event cannot be empty.\n______________________________________________________________________________");
        }
        if(at.equals(description) || at.equals(" ")){
            throw new DukeException("______________________________________________________________________________\n ☹ OOPS!!! The time and date of an event cannot be empty.\n______________________________________________________________________________");
        }
        else{
            this.at = at;
        }
    }
    @Override
    public String toString(){
        return "[E]" + super.toString() + "(at:" + at + ")";
    }

}