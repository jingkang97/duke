/**
 * Represents a task of type ToDo
 * Requires a input after the input "todo"
 */
public class ToDo extends Task {

    /**
     * Creates a new ToDo task with the given description
     * The description cannot be an empty string
     * @param description is a string that describes the task to be done
     * @throws DukeException in cases when the description of the todo task is empty
     */
    public ToDo(String description)throws DukeException{
        super(description);
        if(description.equals("todo ") || description.equals("todo")){
            throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
        }
    }
    @Override
    /**
     * Converts Todo object into a String to be printed out
     * Returns String of Todo object
     */
    public String toString(){
        return "[T]" + super.toString();
    }
}