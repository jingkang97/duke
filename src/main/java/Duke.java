import java.io.BufferedWriter;
import java.util.Scanner;
import java.io.File;
import java.io.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
public class Duke {
    public static void main(String[] args) throws IOException{
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        //Task[] tasks = new Task [100];
        ArrayList<Task> tasks= new ArrayList<>(100);
        //String [] Record = new String [100]; //to be written to file at the end
        ArrayList<String> Record = new ArrayList<>(100);

        int index = 0;
        try{
            File file = new File("/Users/ngjingkang/duke/data/duke.txt");
            Scanner filescan = new Scanner(file);
            filescan.useDelimiter("\n");
            String [] fileContent = new String [100];

            while(filescan.hasNext()){
                fileContent[index] = filescan.next();
                Record.add(fileContent[index]);//= fileContent[index];
                String filetoken[] = fileContent[index].split(",");
                if(filetoken[0].equals("D")){
                    String description = filetoken[2];
                    String by = filetoken[3];
                    tasks.add(new Deadline("deadline " + description, by));// = new Deadline("deadline " + description, by);
                    tasks.get(index).isDone = filetoken[1].equals("true") ? true : false;

                    index += 1;
                }
                else if(filetoken[0].equals("T")){
                    String description = filetoken[2];
                    tasks.add(new ToDo("todo " + description));// = new ToDo("todo " + description);
                    tasks.get(index).isDone = filetoken[1].equals("true") ? true : false;

                    index += 1;
                }
                else if(filetoken[0].equals("E")){
                    String description = filetoken[2];
                    String by = filetoken[3];
                    tasks.add(new Event("event " + description, by));// = new Event("event " + description, by);
                    tasks.get(index).isDone = filetoken[1].equals("true") ? true : false;

                    index += 1;
                }

            }
        }catch(Exception e){
            System.out.println(e);
        }

        Scanner scan = new Scanner(System.in); //taking in input
        BufferedWriter add = new BufferedWriter(new FileWriter("/Users/ngjingkang/duke/data/duke.txt", true));
        while(true){

            String input = scan.nextLine();
            String[] tokens = input.split(" ");
//

            if(tokens[0].equals("done")){
                int result = Integer.parseInt(tokens[1]);
                tasks.get(result - 1).isDone = true;
                System.out.println("Nice! I've marked this task as done: ");
                System.out.println("[" + tasks.get(result - 1).getStatusIcon() + "]" + tasks.get(result - 1).description);
                String newRecord = Record.get(result-1).replace(",false", ",true");
                Record.set(result-1, newRecord);
            }
            else if(tokens[0].equals("remove")){

                int result = Integer.parseInt(tokens[1]);
                String taskToken[] = tasks.get(result-1).toString().split("]");
                String taskType = taskToken[0].equals("[E") ? "E" : taskToken[0].equals("[D") ? "D" : "T";
                System.out.println("Noted. I've removed this task: ");
                System.out.println("[" + taskType + "]" + "[" + tasks.get(result - 1).getStatusIcon() + "]" + tasks.get(result - 1).description);
                Record.remove(result - 1);
                tasks.remove(result - 1);
                index -= 1;
            }
            else if(tokens[0].equals("deadline")){
                String[] token = input.split("/by");
                String[] recordToken = input.split("deadline|/by");
                try{
                    tasks.add(new Deadline(token[0], token[token.length-1].trim()));// = new Deadline(token[0], token[token.length-1].trim());
                    System.out.println("______________________________________________________________________________");
                    System.out.println("Got it. I have added this task: ");
                    System.out.println(tasks.get(index));
                    System.out.println("Now you have " + Integer.toString(index + 1) + " tasks in the list.");
                    System.out.println("______________________________________________________________________________");
                    Record.add("D," + "false," + recordToken[1].trim() + "," + token[token.length-1].trim());
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
                    tasks.add(new Event(token[0], token[token.length-1].trim()));
                    System.out.println("______________________________________________________________________________");
                    System.out.println("Got it. I have added this task: ");
                    System.out.println(tasks.get(index));
                    System.out.println("Now you have " + Integer.toString(index + 1) + " tasks in the list.");
                    System.out.println("______________________________________________________________________________");
                    Record.add("E," + "false," + recordToken[1].trim() + "," + token[token.length-1].trim());
                    index += 1;
                }
                catch(DukeException e){
                    System.out.println(e.getMessage());
                }
            }
            else if(tokens[0].equals("todo")){
                try {
                    tasks.add(new ToDo(input));
                    String[] recordToken = input.split("todo");
                    System.out.println("______________________________________________________________________________");
                    System.out.println("Got it. I have added this task: ");
                    System.out.println(tasks.get(index));
                    System.out.println("Now you have " + Integer.toString(index + 1) + " tasks in the list.");
                    System.out.println("______________________________________________________________________________");

                    Record.add("T," + "false," + recordToken[1].trim());
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
                    for (int i = 0; i < index; i += 1) {
                        System.out.println(i + 1 + "." + tasks.get(i).toString());
                    }
                }
                else{
                    System.out.println(" ______________________________________________________________________________\n ☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n______________________________________________________________________________");
                }
            }
        }
        //Write to file from the Record array
        BufferedWriter writer = new BufferedWriter(new FileWriter("/Users/ngjingkang/duke/data/duke.txt", false));
        for(int l = 0; l < index; l += 1){
            if (Record.get(l) != null && l != 0) {
                writer.write("\n");
                writer.write(Record.get(l));
            }
            else if (Record.get(l) != null && l == 0) {
                writer.write(Record.get(l));
            }
        }
        writer.flush();
        writer.close();
    }
}
