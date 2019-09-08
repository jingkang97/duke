import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
public class TodoTesting {

    @Test
    public void testStringConversion(){
        try{assertEquals("[T]" + "[\u2718]" + " GEH assignment", new ToDo("todo GEH assignment").toString());}
        catch(DukeException e){
            System.out.println("error: " + e);
        }
    }
}
