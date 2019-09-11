import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Test out the ToDo class methods to ensure they are working properly and producing the correct output
 */
public class TodoTesting {

    /**
     * Check whether the toString method produce the correct output after the user input the command in the correct format
     */
    @Test
    public void testStringConversion(){
        try{assertEquals("[T]" + "[\u2718]" + " GEH assignment", new ToDo("todo GEH assignment").toString());}
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
            ToDo todo = new ToDo("todo "); //empty todo description
        });
    }
}
