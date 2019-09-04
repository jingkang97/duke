public class ToDo extends Task {

    public ToDo(String description)throws DukeException{
        super(description);
        if(description.equals("todo ") || description.equals("todo")){
            throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
        }
    }
    @Override
    public String toString(){
        return "[T]" + super.toString();
    }
}