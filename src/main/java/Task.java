public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description; //this.description refers to protected String description , = description (parameter)
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public String toString(){
        return "[" + getStatusIcon() + "] " + description;
    }

}