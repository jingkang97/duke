public class ToDo extends Task {

    public ToDo(String description)throws DukeException{
        super(description);
        if(description.equals("todo ") || description.equals("todo")){
            throw new DukeException("______________________________________________________________________________\n ☹ OOPS!!! The description of a todo cannot be empty.\n______________________________________________________________________________");
        }
    }
    @Override
    public String toString(){
        return "[T]" + super.toString();
    }

}