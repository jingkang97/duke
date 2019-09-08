import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
public class DeadlineTesting {

    String DateTime = "9/9/2019 2359";

    @Test
    public void testStringConversion(){

        try{assertEquals("[D]" + "[\u2718]" + " CG2027 Assignment(by: 9 of September 2019, 11:59PM)", new Deadline("deadline CG2027 Assignment",DateTime).toString());}
        catch(DukeException e){
            System.out.println("error: " + e);
        }
    }

    @Test
    public void testDateFormatConversion(){
        try{assertEquals("9 of September 2019, 11:59PM", new Event("deadline CG2027 Assignment",DateTime).EventFormatDateTime);}
        catch(DukeException e){
            System.out.println("error: " + e);
        }
    }
}
