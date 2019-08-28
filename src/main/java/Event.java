import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task{

    protected String at;
    protected String EventFormatDateTime;

    public Event(String description, String at)throws DukeException{
        super(description);
        if (description.equals("event ") || description.equals("event")) {
            throw new DukeException("______________________________________________________________________________\n ☹ OOPS!!! The description of an event cannot be empty.\n______________________________________________________________________________");
        }
        if (at.equals(description.trim()) || at.equals(" ")) {
            throw new DukeException("______________________________________________________________________________\n ☹ OOPS!!! The time and date of an event cannot be empty.\n______________________________________________________________________________");
        } else {

            try {
                at.trim();
                this.at = at;
                DateTimeFormat(this.at);
                this.EventFormatDateTime = DateTimeFormat(this.at);
            }catch(Exception e){
                throw new DukeException("Please input the date and time in this format: dd/mm/yyyy hhmm");
            }
        }
    }
    @Override
    public String toString(){
        return "[E]" + super.toString() + "(at: " + EventFormatDateTime + ")";
    }
    public String DateTimeFormat(String dateTime){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
        LocalDateTime localDate = LocalDateTime.parse(dateTime, formatter);
        String localDateTimeString = formatter.format(localDate);

        DateTimeFormatter customFormatter = DateTimeFormatter.ofPattern("d'" + ordinalConversion(localDateTimeString) + "' 'of' MMMM yyyy, h':'mma");
        String result = customFormatter.format(localDate);
        return result;
    }

    public String ordinalConversion(String dateTimeFormat){
        String customToken [] = dateTimeFormat.split("/");
        int ordinalNumber = Integer.parseInt(customToken[0]);
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