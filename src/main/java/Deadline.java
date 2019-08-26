public class Deadline extends Task {

    protected String by;

    public Deadline(String description, String by) throws DukeException{
        super(description);
        if(description.equals("deadline ") || description.equals("deadline")){
            throw new DukeException("______________________________________________________________________________\n ☹ OOPS!!! The description of a deadline cannot be empty.\n______________________________________________________________________________");
        }
        if(by.equals(description)){
            throw new DukeException("______________________________________________________________________________\n ☹ OOPS!!! The time and date of a deadline cannot be empty.\n______________________________________________________________________________");
        }
        else{
            this.by = by;
        }

    }
    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by:" + by + ")";//super will use the Parent's/base class method instead of the child's/sub class method
    }
}