public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) throws DukeException{

        if(description.equals("deadline")|| description.equals("todo") || description.equals("event")){
            this.description = description; //this.description refers to protected String description , = description (parameter)
        }
        else{

            String token[] = description.split("deadline|todo|event");
            String intermediate = token[1];
            this.description = intermediate;
        }

        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public String toString(){
        return "[" + getStatusIcon() + "]" + description;
    }

}