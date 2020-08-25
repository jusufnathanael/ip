import java.util.Scanner;

public class Duke {

    public static void printLine(){
        System.out.println("--------------------------------------------------");
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

        Task[] t = new Task[100];

        int index = 0;

        while (!line.equals("bye")){

            printLine();
            if (line.equals("list")){
                for (int i = 0; i < index; i++){
                    t[i].printTask(i);
                }
            }
            else if (line.startsWith("done")){
                int l = Integer.parseInt(line.substring(5, line.length())) - 1;
                t[l].markAsDone();
            }
            else {
                t[index] = new Task(line);
                index++;
                System.out.println("added: " + line);
            }
            printLine();

            line = in.nextLine();
        }

        bye();

    }
}
