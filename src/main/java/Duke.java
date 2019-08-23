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
        String[] todo = new String [100];
        int index = 0;
        while(true){
            String input = scan.nextLine();

            if(input.equals("bye")){
                System.out.println("______________________________________________________________________________");
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println("______________________________________________________________________________");
                break;
            }
            if(input.equals("list")){
                for(int i = 0; i < todo.length; i += 1){
                    if(todo[i]!=null){
                        System.out.println(i+1 + ". " + todo[i]);
                    }
                }
            }
            else{
                todo[index] = input;
                index += 1;
                System.out.println("______________________________________________________________________________");
                System.out.println("added: " + input);
                System.out.println("______________________________________________________________________________");
            }
        }
    }
}