import java.io.*;
import java.util.Scanner;
import java.util.*;

/**
 * Contains methods that deals with the loading of tasks from the file
 */
public class Storage{
    /*File path that leads to the text file which contains the content and data of the task list*/
    private String pathname;
    /*Array of string which reads in the content from the text file and stores them in this array, which will then be added to the actual Task List*/
    private String [] fileContent = new String[100];
    /*current index*/
    private int index = 0;
    /*Creates a taskList of type Task to add the tasks that are stored in the file*/
    private ArrayList<Task> tasks = new ArrayList<>();

    /**
     * Represents a new Storage that reads in pathname of type String to the text file
     * @param pathname file path of type String to the text file that stores the content
     */
    public Storage(String pathname){
        this.pathname = pathname;
    }

    /**
     * Reads in data from the text file and identify the type of tasks from the text file
     * It will then create the Task object accordingly based on its type and add it to the task list
     * @return a Task List which contains the tasks from the current text file
     * @throws DukeException when there is no such text file
     */
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
