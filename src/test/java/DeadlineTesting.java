import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Test out the Deadline class methods to ensure they are working properly and producing the correct output
 */
public class DeadlineTesting {
    /*Date and time of format dd/mm/yyyy HHmm*/
    String DateTime = "9/9/2019 2359";

    /**
     * Check whether the toString method produce the correct output after the user input the command in the correct format
     */
    @Test
    public void testStringConversion(){
        try{assertEquals("[D]" + "[\u2718]" + " CG2027 Assignment(by: 9 of September 2019, 11:59PM)", new Deadline("deadline CG2027 Assignment",DateTime).toString());}
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
        try{assertEquals("9 of September 2019, 11:59PM", new Event("deadline CG2027 Assignment",DateTime).EventFormatDateTime);}
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
           Deadline deadline = new Deadline("deadline ", "9/9/2019, 1800"); //empty deadline description
        });
    }
    /**
     * Checks whether the correct Exception will be thrown when the date format is wrong
     */
    @Test void whenDateExceptionThrown(){
        assertThrows(DukeException.class,() ->{
            Deadline deadline = new Deadline("deadline geh assignment", "9999"); //empty event description
        });
    }
}
