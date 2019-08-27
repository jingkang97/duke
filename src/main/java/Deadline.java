import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    protected String by;
    protected String DeadlineFormatDateTime;
    public Deadline(String description, String by) throws DukeException{
        super(description);
        if(description.equals("deadline ") || description.equals("deadline")){
            throw new DukeException("______________________________________________________________________________\n ☹ OOPS!!! The description of a deadline cannot be empty.\n______________________________________________________________________________");
        }
        if(by.equals(description) || by.equals(" ")){
            throw new DukeException("______________________________________________________________________________\n ☹ OOPS!!! The time and date of a deadline cannot be empty.\n______________________________________________________________________________");
        }
        else{
            by.trim();
            this.by = by;
            DateTimeFormat(this.by);
        }

    }
    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + DeadlineFormatDateTime + ")";//super will use the Parent's/base class method instead of the child's/sub class method
    }

    public void DateTimeFormat(String dateTime){
        try{
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
            LocalDateTime localDate = LocalDateTime.parse(dateTime, formatter);
            DateTimeFormatter customFormatter = DateTimeFormatter.ofPattern("d 'of' MMMM yyyy, h':'mma");;
            this.DeadlineFormatDateTime = customFormatter.format(localDate);
        }catch(Exception e){
            System.out.println("Please input the date and time in this format: dd/mm/yyyy hhmm");
        }
    }
}