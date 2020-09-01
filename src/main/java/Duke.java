import java.util.Scanner;

public class Duke {

    public static void printLine(){
        System.out.println("--------------------------------------------------");
    }

    public static void greet(){
        printLine();
        System.out.println("Hello! I'm Duke\n" + "What can I do for you?");
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

    public static void acknowledgement(Task message, int i){
        System.out.println("Got it. I've added this task:");
        System.out.println("    " + message);
        System.out.println("Now you have " + i + (i == 1 ? " task" : " tasks") + " in your list.");
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


        // main loop
        while (!line.equals("bye")){

            printLine();

            // list
            if (line.equals("list")){
                for (int i = 0; i < index; i++){
                    System.out.print(i+1 + ". ");
                    System.out.println(t[i]);
                }
            }

            // mark as done
            else if (line.startsWith("done")){
                int l = Integer.parseInt(line.substring(5)) - 1;
                t[l].markAsDone();
            }

            // deadline
            else if (line.startsWith("deadline")){
                t[index] = new Deadline(line.substring(9, line.indexOf(" /")),
                        line.substring(line.indexOf("/by") + 4));
                acknowledgement(t[index], index+1);
                index++;
            }

            // todo
            else if (line.startsWith("todo")){
                t[index] = new Todo(line.substring(5));
                acknowledgement(t[index], index+1);
                index++;
            }

            // event
            else if (line.startsWith("event")){
                t[index] = new Event(line.substring(6, line.indexOf(" /")),
                        line.substring(line.indexOf("/at") + 4));
                acknowledgement(t[index], index+1);
                index++;
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
