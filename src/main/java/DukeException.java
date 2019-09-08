/**
 * A custom exception class that deals with exception unique to Duke
 */
public class DukeException extends Exception{
    /**
     * Provides the detail of the exception being thrown
     * @param errorMessage of type String
     */
    public DukeException(String errorMessage){
       super(errorMessage);
    }

}
