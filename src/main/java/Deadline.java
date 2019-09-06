import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    protected String by;
    protected String DeadlineFormatDateTime;

    public Deadline(String description, String by) throws DukeException{
        super(description);
        if(description.equals("deadline ") || description.equals("deadline")){
            throw new DukeException("OOPS!!! The description of a deadline cannot be empty.");
        }
        if(by.equals(description.trim()) || by.equals(" ")){
            throw new DukeException("OOPS!!! The time and date of a deadline cannot be empty.");
        }
        else{
            try {
                by.trim();
                this.by = by;
                DateTimeFormat(this.by);
                this.DeadlineFormatDateTime = DateTimeFormat(this.by);


            }catch(Exception e){
                throw new DukeException("Please input the date and time in this format: dd/mm/yyyy hhmm");
            }
        }
    }
    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + DeadlineFormatDateTime + ")";//super will use the Parent's/base class method instead of the child's/sub class method
    }
    public String DateTimeFormat(String dateTime){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
        LocalDateTime localDate = LocalDateTime.parse(dateTime, formatter);
        DateTimeFormatter customFormatter = DateTimeFormatter.ofPattern("d" + " 'of' MMMM yyyy, h':'mma");
        String result = customFormatter.format(localDate);
        return result;
    }

    public String ordinalConversion(String dateTimeFormat){
        String customToken [] = dateTimeFormat.split("/");
        int ordinalNumber = Integer.parseInt(customToken[0]);
        if(ordinalNumber >= 11 && ordinalNumber <= 13){
            return "th";
        }
        switch(ordinalNumber % 10){
            case 1:
                return "st";
            case 2:
                return "nd";
            case 3:
                return "rd";
            default:
                return "th";
        }
    }
}