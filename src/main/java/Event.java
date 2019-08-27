import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task{

    protected String at;
    protected String EventFormatDateTime;

    public Event(String description, String at)throws DukeException{
        super(description);
        if(description.equals("event ") || description.equals("event")){
            throw new DukeException("______________________________________________________________________________\n ☹ OOPS!!! The description of an event cannot be empty.\n______________________________________________________________________________");
        }
        if(at.equals(description) || at.equals(" ")){
            throw new DukeException("______________________________________________________________________________\n ☹ OOPS!!! The time and date of an event cannot be empty.\n______________________________________________________________________________");
        }
        else{
            at.trim();
            this.at = at;
            DateTimeFormat(this.at);
        }
    }
    @Override
    public String toString(){
        return "[E]" + super.toString() + "(at: " + EventFormatDateTime + ")";
    }
    public void DateTimeFormat(String dateTime){
        try{
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
            LocalDateTime localDate = LocalDateTime.parse(dateTime, formatter);
            DateTimeFormatter customFormatter = DateTimeFormatter.ofPattern("d 'of' MMMM yyyy, h':'mma");;
            this.EventFormatDateTime = customFormatter.format(localDate);
        }catch(Exception e){
            System.out.println("Please input the date and time in this format: dd/mm/yyyy hhmm");
        }
    }

}