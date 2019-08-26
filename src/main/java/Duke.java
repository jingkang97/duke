import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        Scanner scan = new Scanner(System.in);
        Task[] tasks = new Task [100];
        int index = 0;
        while(true){
            String input = scan.nextLine();
            String[] tokens = input.split(" ");
            if(tokens[0].equals("done")){
                int result = Integer.parseInt(tokens[1]);
                tasks[result - 1].isDone = true;
                System.out.println("Nice! I've marked this task as done: ");
                System.out.println("[" + tasks[result - 1].getStatusIcon() + "]" + tasks[result - 1].description);
            }
            if(tokens[0].equals("deadline")){
                String[] token = input.split("/by");
                try{
                    tasks[index] = new Deadline(token[0], token[token.length-1]);
                    System.out.println("______________________________________________________________________________");
                    System.out.println("Got it. I have added this task: ");
                    System.out.println(tasks[index]);
                    System.out.println("Now you have " + Integer.toString(index + 1) + " tasks in the list.");
                    System.out.println("______________________________________________________________________________");
                    index += 1;
                }
                catch(DukeException e){
                    System.out.println(e.getMessage());
                }
            }
            if(tokens[0].equals("event")){
                String[] token = input.split("/at");
                try {
                    tasks[index] = new Event(token[0], token[token.length-1]);
                    System.out.println("______________________________________________________________________________");
                    System.out.println("Got it. I have added this task: ");
                    System.out.println(tasks[index]);
                    System.out.println("Now you have " + Integer.toString(index + 1) + " tasks in the list.");
                    System.out.println("______________________________________________________________________________");
                    index += 1;
                }
                catch(DukeException e){
                    System.out.println(e.getMessage());
                }
            }
            if(tokens[0].equals("todo")){
                try {
                    tasks[index] = new ToDo(input);
                    System.out.println("______________________________________________________________________________");
                    System.out.println("Got it. I have added this task: ");
                    System.out.println(tasks[index]);
                    System.out.println("Now you have " + Integer.toString(index + 1) + " tasks in the list.");
                    System.out.println("______________________________________________________________________________");
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
                    System.out.println(" ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                }

            }
        }
    }
}
