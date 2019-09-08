import java.util.Scanner;

/**
 * A personal virtual assistant
 */
public class Duke {

    /*Storage object*/
    private Storage storage;
    /*TaskList object*/
    private TaskList tasks;
    /*Ui object*/
    private Ui ui;
    /*Scanner to take in input*/
    private static Scanner scan = new Scanner(System.in);

    /**
     * Creates a new Duke that will then read the corresponding filePath to load the content
     * of the task list
     * @param filePath file path of type String to the text file that stores the content
     */
    public Duke(String filePath){
        ui = new Ui();
        storage = new Storage(filePath);
        try{
            tasks = new TaskList(storage.load());
        }catch(DukeException e){
            System.out.println(e.getMessage());
            tasks = new TaskList();
        }
    }

    /**
     * This is the function of Duke that will read in input
     * from the user and execute the task accordingly depending
     * on the command until the user exits the program by inputting "bye"
     */
    public void run(){
        ui.showWelcome();
        boolean isExit = false;

        while(!isExit && scan.hasNextLine()){
            try {
                String input = scan.nextLine();
                ui.showLine();
                Parser parser = new Parser(input);
                parser.executeCommand(tasks);
                isExit = parser.isExit();
            }
            catch(Exception e){
                ui.showLoadingError(e.getMessage());
            }
            finally{
                ui.showLine();
            }
        }
    }

    /**
     * The main function which is the entry point of the program
     * @param args
     */
    public static void main(String[] args){
        new Duke("/Users/ngjingkang/duke/data/duke.txt").run();
    }
}
