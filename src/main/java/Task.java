/**
 * Represents a Task in the TodoList
 * There are three types of tasks: deadline, event and todo
 */
public class Task {

    //Task fields
    protected String description;
    protected boolean isDone;
    protected String type;

    /**
     * Creates new Task with the given description
     * Sets the status of the task to be false (not done)
     * Sets the type base on the description of the task
     * @param description description of the task which is of type String and should not be empty
     */
    public Task(String description){

        if(description.equals("deadline")|| description.equals("todo") || description.equals("event")){
            this.description = description; //this.description refers to protected String description , = description (parameter)
        }
        else{
            this.type = description;
            String token[] = description.split("deadline|todo|event");
            String intermediate = token[1];
            this.description = intermediate;
        }

        this.isDone = false;
    }

    /**
     * Returns the corresponding tick or cross symbol
     * @return tick if isDone is true and a cross if isDone is false
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    /**
     * Converts task object into a string with the status (done or not done) and description of the task to be printed out
     * @return a string of the task object with the status and description of the task
     */
    public String toString(){
        return "[" + getStatusIcon() + "]" + description;
    }
}