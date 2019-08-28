import java.io.BufferedWriter;
import java.util.Scanner;
import java.io.File;
import java.io.*;
public class Duke {
    public static void main(String[] args) throws IOException{
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        Task[] tasks = new Task [100];
        String [] Record = new String [100]; //to be written to file at the end
        int index = 0;
        try{
            File file = new File("/Users/ngjingkang/duke/data/duke.txt");
            Scanner filescan = new Scanner(file);
            filescan.useDelimiter("\n");
            String [] fileContent = new String [100];

            while(filescan.hasNext()){
                fileContent[index] = filescan.next();
                Record[index] = fileContent[index];
                String filetoken[] = fileContent[index].split(",");
                if(filetoken[0].equals("D")){
                    String description = filetoken[2];
                    String by = " " + filetoken[3];
                    tasks[index] = new Deadline("deadline " + description, by);
                    tasks[index].isDone = filetoken[1].equals("true") ? true : false;;
                }
                if(filetoken[0].equals("T")){
                    String description = filetoken[2];
                    tasks[index] = new ToDo("todo " + description);
                    tasks[index].isDone = filetoken[1].equals("true") ? true : false;
                }
                if(filetoken[0].equals("E")){
                    String description = filetoken[2];
                    String by = " " + filetoken[3];
                    tasks[index] = new Event("event " + description, by);
                    tasks[index].isDone = filetoken[1].equals("true") ? true : false;
                }
                index += 1;
            }
        }catch(Exception e){
            System.out.println(e);
        }
        Scanner scan = new Scanner(System.in); //taking in input
        BufferedWriter add = new BufferedWriter(new FileWriter("/Users/ngjingkang/duke/data/duke.txt", true));
        while(true){

            String input = scan.nextLine();
            String[] tokens = input.split(" ");
            if(tokens[0].equals("done")){
                int result = Integer.parseInt(tokens[1]);
                tasks[result - 1].isDone = true;
                System.out.println("Nice! I've marked this task as done: ");
                System.out.println("[" + tasks[result - 1].getStatusIcon() + "]" + tasks[result - 1].description);
                String newRecord = Record[result - 1].replace(",false", ",true");
                Record[result-1] = newRecord;
            }
            else if(tokens[0].equals("deadline")){
                String[] token = input.split("/by");
                String[] recordToken = input.split("deadline|/by");
                try{
                    tasks[index] = new Deadline(token[0], token[token.length-1]);
                    System.out.println("______________________________________________________________________________");
                    System.out.println("Got it. I have added this task: ");
                    System.out.println(tasks[index]);
                    System.out.println("Now you have " + Integer.toString(index + 1) + " tasks in the list.");
                    System.out.println("______________________________________________________________________________");
                    Record[index] = "D," + "false," + recordToken[1].trim() + "," + token[token.length-1].trim();
                    index += 1;

                }
                catch(DukeException e){
                    System.out.println(e.getMessage());
                }
            }
            else if(tokens[0].equals("event")){
                String[] token = input.split("/at");
                String[] recordToken = input.split("event|/at");
                try {
                    tasks[index] = new Event(token[0], token[token.length-1]);
                    System.out.println("______________________________________________________________________________");
                    System.out.println("Got it. I have added this task: ");
                    System.out.println(tasks[index]);
                    System.out.println("Now you have " + Integer.toString(index + 1) + " tasks in the list.");
                    System.out.println("______________________________________________________________________________");
                    Record[index] = "E," + "false," + recordToken[1].trim() + "," + token[token.length-1].trim();
                    index += 1;
                }
                catch(DukeException e){
                    System.out.println(e.getMessage());
                }
            }
            else if(tokens[0].equals("todo")){
                try {
                    tasks[index] = new ToDo(input);
                    String[] recordToken = input.split("todo");
                    System.out.println("______________________________________________________________________________");
                    System.out.println("Got it. I have added this task: ");
                    System.out.println(tasks[index]);
                    System.out.println("Now you have " + Integer.toString(index + 1) + " tasks in the list.");
                    System.out.println("______________________________________________________________________________");

                    Record[index] = "T," + "false," + recordToken[1].trim();
                    index += 1;
                }
                catch(DukeException e){
                    System.out.println(e.getMessage());
                }
            }
            else {
                if (input.equals("bye")) {
                    System.out.println("______________________________________________________________________________");
                    System.out.println("Bye. Hope to see you again soon!");
                    System.out.println("______________________________________________________________________________");
                    break;
                }
                if (input.equals("list")) {
                    System.out.println("Here are the tasks in you list:");
                    for (int i = 0; i < tasks.length; i += 1) {
                        if (tasks[i] != null) {
                            System.out.println(i + 1 + "." +  tasks[i].toString());
                        }
                    }
                }
                else{
                    System.out.println(" ______________________________________________________________________________\n ☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n______________________________________________________________________________");
                }
            }
        }
        //Write to file from the Record array
        BufferedWriter writer = new BufferedWriter(new FileWriter("/Users/ngjingkang/duke/data/duke.txt", false));
        for(int l = 0; l < Record.length; l += 1){
            if (Record[l] != null && l != 0) {
                writer.write("\n");
                writer.write(Record[l]);
            }
            else if (Record[l] != null && l == 0) {
                writer.write(Record[l]);
            }
        }
        writer.flush();
        writer.close();
    }
}