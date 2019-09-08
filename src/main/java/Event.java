import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task of type event
 * Requires a input after the input "event"
 * and a due date of format dd/mm/yyyy hhmm after the input "/at"
 */
public class Event extends Task{
    /* the date of the event*/
    protected String at;
    /* the formatted date and time*/
    protected String EventFormatDateTime;

    /**
     * Creates a new Event task with the given description
     * The description and date cannot be an empty string
     * @param description is a string that describes the event happening
     * @param at is a string of format dd/mm/yyyy hhmm that describes the date and time of the event happening
     * @throws DukeException in cases when the description of event and description of the date and time is empty
     */
    public Event(String description, String at)throws DukeException{
        super(description);
        if (description.equals("event ") || description.equals("event")) {
            throw new DukeException("OOPS!!! The description of an event cannot be empty.");
        }
        if (at.equals(description.trim()) || at.equals(" ")) {
            throw new DukeException("OOPS!!! The time and date of an event cannot be empty.");
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
    /**
     * Converts Event object into a String to be printed out
     * Returns String of Event object
     */
    public String toString(){
        return "[E]" + super.toString() + "(at: " + EventFormatDateTime + ")";
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