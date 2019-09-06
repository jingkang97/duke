import java.util.NoSuchElementException;
import java.util.Scanner;

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private static Scanner scan = new Scanner(System.in);

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
    public static void main(String[] args){
        new Duke("/Users/ngjingkang/duke/data/duke.txt").run();
    }
}