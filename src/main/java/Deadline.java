import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task of type deadline
 * Requires a input after the input "deadline"
 * and a due date of format dd/mm/yyyy hhmm after the input "/by"
 */
public class Deadline extends Task {

    /* the due date of the task*/
    protected String by;
    /* the formatted date and time*/
    protected String DeadlineFormatDateTime;

    /**
     * Creates a new Deadline task with the given description
     * The description and date cannot be an empty string
     * @param description is a string that describes the task to be done
     * @param by is a string of format dd/mm/yyyy hhmm that describes the due date(including time) of the task to be completed
     * @throws DukeException in cases when the description of task and description of the date and time is empty
     */
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
    /**
     * Converts Deadline object into a String to be printed out
     * Returns String of Deadline object
     */
    public String toString() {
        return "[D]" + super.toString() + "(by: " + DeadlineFormatDateTime + ")";//super will use the Parent's/base class method instead of the child's/sub class method
    }

    /**
     * Converts the date and time input format of dd/mm/yyyy HHmm to format d of MMMM yyyy, h:mma
     * @param dateTime is a string of format dd/mm/yyyy HHmm
     * @return a string of date and time in the format d of MMMM yyyy, h:mma
     */
    public String DateTimeFormat(String dateTime){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
        LocalDateTime localDate = LocalDateTime.parse(dateTime, formatter);
        DateTimeFormatter customFormatter = DateTimeFormatter.ofPattern("d" + " 'of' MMMM yyyy, h':'mma");
        String result = customFormatter.format(localDate);
        return result;
    }

    /**
     * Adds the ordinal suffix to the day
     * @param dateTimeFormat is a string of format dd/mm/yyyy HHmm
     * @return a suffix of type String of either st, nd, rd or th depending on the value of the day
     */
    public String ordinalConversion(String dateTimeFormat){
        String customToken [] = dateTimeFormat.split("/");
        /*converted String type number to int */
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