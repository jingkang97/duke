public class Deadline extends Task {

    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override //ability of a subclass to override a method allows a subclass to inherit from a superclass whose behavior is "close enough" and then to modify behavior as needed
    //access modifier for an overriding method can allow more, but not less, access than the overridden method.
    //e.g. a protected instance method in super-class can be made public, but not private in the subclass
    //final/private(generate new method in subclass)/static(hides method in subclass) methods in super-class cannot be overridden
    //cannot override constructor
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")"; //super will use the Parent's/base class method instead of the child's/sub class method
    }
}