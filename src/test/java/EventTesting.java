import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Test out the Event class methods to ensure they are working properly and producing the correct output
 */
public class EventTesting {
    /*Date and time of format dd/mm/yyyy HHmm*/
    String DateTime = "30/8/2019 1900";

    /**
     * Check whether the toString method produce the correct output after the user input the command in the correct format
     */
    @Test
    public void testStringConversion(){
        try{assertEquals("[E]" + "[\u2718]" + " Orbital(at: 30 of August 2019, 7:00PM)", new Event("event Orbital",DateTime).toString());}
        catch(DukeException e){
            System.out.println("error: " + e);
        }
    }
    /**
     * Check whether the DateFormatConversion method produce the correct format of d of MMMM yyyy, h:mma after the user input
     * date format of dd/mm/yyyy HHmm
     */
    @Test
    public void testDateFormatConversion(){
        try{assertEquals("30 of August 2019, 7:00PM", new Event("event Orbital",DateTime).EventFormatDateTime);}
        catch(DukeException e){
            System.out.println("error: " + e);
        }
    }
    /**
     * Checks whether the correct Exception will be thrown when the input is wrong
     */
    @Test
    public void whenExceptionThrown(){
        assertThrows(DukeException.class,() ->{
            Event event = new Event("event ", "9/9/2019, 1800"); //empty event description
        });
    }
    /**
     * Checks whether the correct Exception will be thrown when the date format is wrong
     */
    @Test void whenDateExceptionThrown(){
        assertThrows(DukeException.class,() ->{
            Event event = new Event("event orbital", "9999"); //wrong date time format
        });
    }
}

