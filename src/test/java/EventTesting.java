import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
public class EventTesting {

    String DateTime = "30/8/2019 1900";

    @Test
    public void testStringConversion(){
        try{assertEquals("[E]" + "[\u2718]" + " Orbital(at: 30 of August 2019, 7:00PM)", new Event("event Orbital",DateTime).toString());}
        catch(DukeException e){
            System.out.println("error: " + e);
        }
    }

    @Test
    public void testDateFormatConversion(){
        try{assertEquals("30 of August 2019, 7:00PM", new Event("event Orbital",DateTime).EventFormatDateTime);}
        catch(DukeException e){
            System.out.println("error: " + e);
        }
    }
}
