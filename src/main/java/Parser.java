import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Deals with making sense of the user command and saving tasks in the file
 */
public class Parser {
    /*Input from the user*/
    private String command;
    /*The number of elements in the array*/
    private int sizeOfArray;
    /*Checks whether the user have requested to exit the program*/
    private boolean isExit = false;
    /*File path that leads to the text file which contains the content and data of the task list*/
    private String pathname = "/Users/ngjingkang/duke/data/duke.txt";
    /*File path that creates a temporary text file for removing task and marking tasks as done*/
    private String temporaryPathName = "/Users/ngjingkang/duke/data/tempDuke.txt";

    /**
     * Represents a new Parser that takes in command of type String from the user input
     * @param command input from the user of type String
     */
    public Parser(String command){
        this.command = command;
    }

    /**
     * Takes in all the command from the user and executes the corresponding command based on the input from the user
     * "deadline" - it will create and add a task of type deadline with task description and due date to the task list
     * "event" - it will create and add a task of type event with task description and date of event to the task list
     *  "todo" - it will create and add a task of type todo with task description to the task list
     *  "find" - it will find the corresponding tasks in the list that contains those keywords and list them out
     *  "list" - it will list out the current task list
     *  "done" - it will mark the task as done and print out a tick
     *  "delete" - it will remove the particular task from the task list
     *  "bye" - it will exit the loop and hence, program
     *  In the case whereby the input does not match any of those command, it will throw an exception
     *  and inform the user that no such command exist
     * @param tasks the current ArrayList of tasks
     */
    public void executeCommand(TaskList tasks){
        String[] tokens = command.split(" ");
        sizeOfArray = tasks.sizeOfList();
        switch(tokens[0]){
            case "deadline": {
                try {
                    String[] token = command.split("/by");
                    String[] fileToken = command.split("deadline|/by");
                    tasks.add(new Deadline(token[0], token[token.length - 1].trim()));
                    System.out.println("Got it. I have added this task: ");
                    System.out.println(tasks.get(sizeOfArray));
                    System.out.println("Now you have " + Integer.toString(sizeOfArray + 1) + " tasks in the list.");
                    try{
                        BufferedWriter writer = new BufferedWriter(new FileWriter(pathname, true));
                        writer.write("D,false," + fileToken[1].trim() + "," + token[token.length-1].trim());
                        writer.newLine();
                        writer.flush();
                        writer.close();
                    }
                    catch(IOException e){
                        System.out.println("Unable to write to file: " + e);
                    }
                } catch (DukeException e) {
                    System.out.println(e.getMessage());
                }
            }
                break;
            case "event": {
                try {
                    String[] token = command.split("/at");
                    String[] fileToken = command.split("event|/at");
                    tasks.add(new Event(token[0], token[token.length - 1].trim()));
                    System.out.println("Got it. I have added this task: ");
                    System.out.println(tasks.get(sizeOfArray));
                    System.out.println("Now you have " + Integer.toString(sizeOfArray + 1) + " tasks in the list.");
                    try{
                        BufferedWriter writer = new BufferedWriter(new FileWriter(pathname, true));
                        writer.write("E,false," + fileToken[1].trim() + "," + token[token.length-1].trim());
                        writer.newLine();
                        writer.flush();
                        writer.close();
                    }
                    catch(IOException e){
                        System.out.println("Unable to write to file: " + e);
                    }
                } catch (DukeException e) {
                    System.out.println(e.getMessage());
                }
            }
                break;
            case "todo": {
                try {
                    tasks.add(new ToDo(command));
                    String[] fileToken = command.split("todo");
                    System.out.println("Got it. I have added this task: ");
                    System.out.println(tasks.get(sizeOfArray));
                    System.out.println("Now you have " + Integer.toString(sizeOfArray + 1) + " tasks in the list.");
                    try{
                        BufferedWriter writer = new BufferedWriter(new FileWriter(pathname, true));
                        writer.write("\nT,false," + fileToken[1].trim());
                        writer.newLine();
                        writer.flush();
                        writer.close();
                    }
                    catch(IOException e){
                        System.out.println("Unable to write to file: " + e);
                    }
                } catch (DukeException e) {
                    System.out.println(e.getMessage());
                }
            }
            break;
            case "find": {
                try {
                    String findToken[] = command.split("find");
                    String search = findToken[1].trim();
                    System.out.println(tokens[1]);
                    if (tokens[1].isEmpty()) {
                        System.out.println("OOPS!!! The description of a search cannot be empty.");
                    }
                    else{
                        int i = 1;
                        System.out.println("Here are the matching tasks in your list:");
                        for (int n = 0; n < tasks.sizeOfList(); n += 1) {
                            String line = tasks.get(n).description;
                            if (line.contains(search)) {
                                System.out.println(i + "." + tasks.get(n));
                                i += 1;
                            }
                        }
                    }
                } catch (Exception e) {
                    System.out.println("OOPS!!! The task you want to find cannot be empty.");
                }
            }
                break;
            case "delete": {
                int result = Integer.parseInt(tokens[1]);
                String taskToken[] = tasks.get(result - 1).toString().split("]");
                String taskType = taskToken[0].equals("[E") ? "E" : taskToken[0].equals("[D") ? "D" : "T";
                System.out.println("Noted. I've removed this task: ");
                File inputFile = new File(pathname);
                File tempFile = new File(temporaryPathName);
                if (taskType.equals("D")) {
                    String timeDate[] = tasks.get(result - 1).toString().split("by: |\\)");
                    String by = timeDate[1].trim();
                    String datetime = by;
                    String formatDateTime = convertDateTime(datetime);
                    System.out.println("[" + taskType + "]" + "[" + tasks.get(result - 1).getStatusIcon() + "]" + tasks.get(result - 1).description + "(by: " + by + ")");
                    try {
                        BufferedReader reader = new BufferedReader(new FileReader(inputFile));
                        BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));
                        String lineToRemove = "D," + String.valueOf(tasks.get(result-1).isDone).trim() + "," + tasks.get(result-1).description.trim() + "," + formatDateTime;
                        String currentLine;
                        while((currentLine = reader.readLine()) != null){
                            if(!currentLine.equals(lineToRemove)){
                                writer.write(currentLine);
                                writer.newLine();
                            }
                        }
                        writer.flush();
                        inputFile.delete();
                        tempFile.renameTo(inputFile);
                    }
                    catch(IOException e){
                        System.out.println("Unable to read or write file: " + e);
                    }
                } else if (taskType.equals("E")) {
                    String timeDate[] = tasks.get(result - 1).toString().split("at: |\\)");
                    String at = timeDate[1];
                    String datetime = at;
                    String formatDateTime = convertDateTime(datetime);
                    System.out.println("[" + taskType + "]" + "[" + tasks.get(result - 1).getStatusIcon() + "]" + tasks.get(result - 1).description + "(at: " + at + ")");
                    try {
                        BufferedReader reader = new BufferedReader(new FileReader(inputFile));
                        BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));
                        String lineToRemove = "E," + String.valueOf(tasks.get(result-1).isDone).trim() + "," + tasks.get(result-1).description.trim() + "," + formatDateTime;
                        String currentLine;
                        while((currentLine = reader.readLine()) != null){
                            if(!currentLine.equals(lineToRemove)){
                                writer.write(currentLine);
                                writer.newLine();
                            }
                        }
                        writer.flush();
                        inputFile.delete();
                        tempFile.renameTo(inputFile);
                    }catch(IOException e){
                        System.out.println("Unable to read or write file: " + e);
                    }
                } else {
                    System.out.println("[" + taskType + "]" + "[" + tasks.get(result - 1).getStatusIcon() + "]" + tasks.get(result - 1).description);
                    try {
                        BufferedReader reader = new BufferedReader(new FileReader(inputFile));
                        BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));
                        String lineToRemove = "T," + String.valueOf(tasks.get(result-1).isDone).trim() + "," + tasks.get(result-1).description.trim();
                        String currentLine;
                        while((currentLine = reader.readLine()) != null){
                            if(!currentLine.equals(lineToRemove)){
                                writer.write(currentLine);
                                writer.newLine();
                            }
                        }
                        writer.flush();
                        inputFile.delete();
                        tempFile.renameTo(inputFile);
                    }catch(IOException e){
                        System.out.println("Unable to read or write file: " + e);
                    }
                }
                tasks.remove(result - 1);
                System.out.println("Now you have " + tasks.sizeOfList() + " tasks in your list.");
            }
                break;
            case "list":{
                System.out.println("Here are the tasks in you list:");
                for (int i = 0; i < sizeOfArray; i += 1) {
                    System.out.println(i + 1 + "." + tasks.get(i).toString());
                }
            }
                break;
            case "done": {
                int result = Integer.parseInt(tokens[1]);
                String taskType[] = tasks.get(result-1).type.split(" ");
                File inputFile = new File(pathname);
                File tempFile = new File(temporaryPathName);
                if (taskType[0].equals("deadline")) {
                    String timeDate[] = tasks.get(result - 1).toString().split("by: |\\)");
                    String by = timeDate[1].trim();
                    String datetime = by;
                    String formatDateTime = convertDateTime(datetime);
                    try {
                        BufferedReader reader = new BufferedReader(new FileReader(inputFile));
                        BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));
                        String lineToRemove = "D," + String.valueOf(tasks.get(result-1).isDone).trim() + "," + tasks.get(result-1).description.trim() + "," + formatDateTime;
                        String currentLine;
                        while((currentLine = reader.readLine()) != null){
                            if(currentLine.equals(lineToRemove)){
                                writer.write("D,true," + tasks.get(result-1).description.trim() + "," + formatDateTime);
                                writer.newLine();
                            }
                            else if(!currentLine.equals(lineToRemove)){
                                writer.write(currentLine);
                                writer.newLine();
                            }
                        }
                        writer.flush();
                        inputFile.delete();
                        tempFile.renameTo(inputFile);
                    }
                    catch(IOException e){
                        System.out.println("Unable to read or write file: " + e);
                    }
                } else if (taskType[0].equals("event")) {
                    String timeDate[] = tasks.get(result - 1).toString().split("at: |\\)");
                    String at = timeDate[1];
                    String datetime = at;
                    String formatDateTime = convertDateTime(datetime);
                    try {
                        BufferedReader reader = new BufferedReader(new FileReader(inputFile));
                        BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));
                        String lineToRemove = "E," + String.valueOf(tasks.get(result-1).isDone).trim() + "," + tasks.get(result-1).description.trim() + "," + formatDateTime;
                        String currentLine;
                        while((currentLine = reader.readLine()) != null){
                            if(currentLine.equals(lineToRemove)){
                                writer.write("E,true," + tasks.get(result-1).description.trim() + "," + formatDateTime);
                                writer.newLine();
                            }
                            else if(!currentLine.equals(lineToRemove)){
                                writer.write(currentLine);
                                writer.newLine();
                            }
                        }
                        writer.flush();
                        inputFile.delete();
                        tempFile.renameTo(inputFile);
                    }catch(IOException e){
                        System.out.println("Unable to read or write file: " + e);
                    }
                } else {
                    try {
                        BufferedReader reader = new BufferedReader(new FileReader(inputFile));
                        BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));
                        String lineToRemove = "T," + String.valueOf(tasks.get(result-1).isDone).trim() + "," + tasks.get(result-1).description.trim();
                        String currentLine;
                        while((currentLine = reader.readLine()) != null){
                            if(currentLine.equals(lineToRemove)){
                                writer.write("T,true," + tasks.get(result-1).description.trim());
                                writer.newLine();
                            }
                            else if(!currentLine.equals(lineToRemove)){
                                writer.write(currentLine);
                                writer.newLine();
                            }
                        }
                        writer.flush();
                        inputFile.delete();
                        tempFile.renameTo(inputFile);
                    }catch(IOException e){
                        System.out.println("Unable to read or write file: " + e);
                    }
                }
                System.out.println("Nice! I've marked this task as done: ");
                tasks.get(result - 1).isDone = true;
                System.out.println("[" + tasks.get(result - 1).getStatusIcon() + "]" + tasks.get(result - 1).description);
            }
                break;
            case "bye": {
                System.out.println("Bye. Hope to see you again soon!");
                this.isExit = true;
            }
                break;
            default: {
                System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
                break;
        }
    }
    /**
     * Converts the date and time input format d of MMMM yyyy, h:mma to dd/mm/yyyy HHmm
     * @param dateTime is a string of format d of MMMM yyyy, h:mma
     * @return a string of date and time in the format dd/mm/yyyy HHmm
     */
    public String convertDateTime(String dateTime){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d 'of' MMMM yyyy, h':'mma");
        LocalDateTime localDate = LocalDateTime.parse(dateTime, formatter);
        DateTimeFormatter customFormatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
        String formatDateTime = customFormatter.format(localDate);
        return formatDateTime;
    }

    /**
     * Checks if user have keyed in "bye" in the input
     * @return true if user input "bye" and returns false if there is not such input
     */
    public boolean isExit(){
        return isExit;
    }
}
