public class Ui {

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
    public void showLoadingError(String errorMessage){
        System.out.println(errorMessage);
    }
    public void showLine(){
        System.out.println("____________________________________________________________");
    }
}