import java.io.*;
import java.util.Scanner;
import java.util.*;

/**
 * Contains methods that deals with the loading of tasks from the file
 */
public class Storage{
    /**/
    private String pathname;
    private String [] fileContent = new String[100];
    private int index = 0;
    private ArrayList<Task> tasks = new ArrayList<>();
    public Storage(String pathname){
        this.pathname = pathname;
    }
    public ArrayList<Task> load()throws DukeException{
        try {
            File file = new File(pathname);
            Scanner fileScan = new Scanner(file, "UTF-8");
            fileScan.useDelimiter("\n");
            while(fileScan.hasNextLine()){
                fileContent[index] = fileScan.nextLine();
                String filetoken[] = fileContent[index].split(",");
                if(filetoken[0].equals("D")){
                    String description = filetoken[2];
                    String by = filetoken[3];
                    tasks.add(new Deadline("deadline " + description, by));
                    tasks.get(index).isDone = filetoken[1].equals("true");
                    index += 1;
                }
                else if(filetoken[0].equals("T")){
                    String description = filetoken[2];
                    tasks.add(new ToDo("todo " + description));
                    tasks.get(index).isDone = filetoken[1].equals("true");
                    index += 1;
                }
                else if(filetoken[0].equals("E")){
                    String description = filetoken[2];
                    String by = filetoken[3];
                    tasks.add(new Event("event " + description, by));
                    tasks.get(index).isDone = filetoken[1].equals("true");
                    index += 1;
                }
            }
            return tasks;
        }catch(IOException e){
            throw new DukeException("File could not be found");
        }
    }
}
