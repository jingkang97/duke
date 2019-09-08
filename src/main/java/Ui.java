/**
 * Extract out the methods that deals with interactions with the user
 */
public class Ui {
    /**
     * Display welcome message
     */
    public void showWelcome(){
        Ui ui = new Ui();
        ui.showLine();
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        ui.showLine();
    }

    /**
     * Shows error message whenever an exception is thrown
     * @param errorMessage error message of type String
     */
    public void showLoadingError(String errorMessage){
        System.out.println(errorMessage);
    }

    /**
     * Prints out line separator
     */
    public void showLine(){
        System.out.println("____________________________________________________________");
    }
}