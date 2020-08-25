import java.util.Scanner;

public class Duke {

    public static void printLine(){
        System.out.println("______________________________________________________________________");
    }

    public static void greet(){
        printLine();
        System.out.println("Hello! I'm Duke\n" +
                "What can I do for you?");
        printLine();
    }

    public static void echo(String line){
        printLine();
        System.out.println(line);
        printLine();
    }

    public static void bye(){
        printLine();
        System.out.println("Bye. Hope to see you again soon!");
        printLine();
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        greet();
        Scanner in = new Scanner(System.in);
        String line = in.nextLine();

        while (!line.equals("bye")){
            echo(line);
            line = in.nextLine();
        }

        bye();

    }
}
