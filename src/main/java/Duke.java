import java.util.Scanner;

public class Duke {

    public static void printLine(){
        System.out.println("------------------------------------------------------" +
                "--------------------------");
    }

    public static void greet(){
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        printLine();
        System.out.println("Hello! I'm Duke\n" + "What can I do for you?");
        printLine();
    }

    public static void bye(){
        printLine();
        System.out.println("Bye. Hope to see you again soon!");
        printLine();
    }

    public static int addTask(Task[] t, String message, int index) throws DukeException {

        // list
        if (message.equals("list")){
            for (int i = 0; i < index; i++){
                System.out.print(i+1 + ". ");
                System.out.println(t[i]);
            }
        }

        // mark as done
        else if (message.startsWith("done")){
            int l = Integer.parseInt(message.substring(5)) - 1;
            t[l].markAsDone();
        }

        // new deadline
        else if (message.startsWith("deadline")){
            t[index] = new Deadline(message.substring(9, message.indexOf(" /")),
                    message.substring(message.indexOf("/by") + 4));
            acknowledgement(t[index], index+1);
            index++;
        }

        // new todo
        else if (message.startsWith("todo")){
            t[index] = new Todo(message.substring(5));
            acknowledgement(t[index], index+1);
            index++;
        }

        // new event
        else if (message.startsWith("event")){
            t[index] = new Event(message.substring(6, message.indexOf(" /")),
                    message.substring(message.indexOf("/at") + 4));
            acknowledgement(t[index], index+1);
            index++;
        }

        // throw exception
        else {
            throw new DukeException();
        }

        return index;
    }

    public static void acknowledgement(Task message, int i){
        System.out.println("Got it. I've added this task:");
        System.out.println("    " + message);
        System.out.println("Now you have " + i + (i == 1 ? " task" : " tasks") + " in your list.");
    }


    public static void main(String[] args) {

        Task[] t = new Task[100];
        int index = 0;

        greet();

        Scanner in = new Scanner(System.in);
        String message = in.nextLine();


        // main loop
        while (!message.equals("bye")){

            printLine();
            try {
                index = addTask(t, message, index);
            }
            catch (DukeException e){
                System.out.println("OOPS!!! I'm sorry, but I don't know what that means.");
            }
            catch (StringIndexOutOfBoundsException e){
                System.out.println("OOPS!!! The description of a " + message + " cannot be empty.");
            }
            printLine();

            message = in.nextLine();
        }

        bye();
    }

}
