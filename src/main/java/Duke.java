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
        while(true){
            String input = scan.next();
            if(input.equals("bye")){
                System.out.println("______________________________________________________________________________");
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println("______________________________________________________________________________");
                break;
            }
            System.out.println("______________________________________________________________________________");
            System.out.println(input);
            System.out.println("______________________________________________________________________________");
        }

    }
}