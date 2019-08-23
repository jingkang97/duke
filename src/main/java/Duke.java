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
        Task[] todo = new Task [100];
        int index = 0;
        while(true){
            String input = scan.nextLine();
            String[] tokens = input.split(" ");
            if(tokens[0].equals("done")){
                int result = Integer.parseInt(tokens[1]);
                todo[result - 1].isDone = true;
                System.out.println("Nice! I've marked this task as done: ");
                System.out.println("[" + todo[result - 1].getStatusIcon() + "] " + todo[result - 1].description);
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
                    for (int i = 0; i < todo.length; i += 1) {
                        if (todo[i] != null) {
                            System.out.println(i + 1 + ".[" + todo[i].getStatusIcon() + "] " + todo[i].description);
                        }
                    }
                } else {
                    todo[index] = new Task(input);
                    index += 1;
                    System.out.println("______________________________________________________________________________");
                    System.out.println("added: " + input);
                    System.out.println("______________________________________________________________________________");
                }
            }
        }
    }
}
