import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

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

    private static int initialisation(ArrayList<Task> t, int index){

        try {
            index = readFile("data/data.txt", t);
        }
        catch (FileNotFoundException e){
            System.out.println("File not found");
        }
        return index;
    }

    private static int readFile(String filePath, ArrayList<Task> t)
            throws FileNotFoundException {

        File f = new File(filePath);
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        System.out.println("yes");
        int index = 0;

        while (s.hasNext()) {

            String temp = s.nextLine();
            System.out.println(temp);
            Task newTask = null;

            if (temp.startsWith("[T]")){
                newTask = new Todo(temp.substring(7));
                t.add(newTask);
            }

            else if (temp.startsWith("[E]")){
                newTask = new Event(temp.substring(7, temp.indexOf(" (")),
                        temp.substring(temp.indexOf("(at:") + 5, temp.indexOf(")")));
                t.add(newTask);
            }

            else if (temp.startsWith("[D]")){
                newTask = new Deadline(temp.substring(7, temp.indexOf(" (")),
                        temp.substring(temp.indexOf("(by:") + 5, temp.indexOf(")")));
                t.add(newTask);
            }

            index++;
        }
        return index;
    }


    public static int addTask(ArrayList<Task> t, String message, int index) throws DukeException {

        // list
        if (message.equals("list")){
            for (int i = 0; i < index; i++) {
                System.out.print(i + 1 + ". ");
                System.out.println(t.get(i));
            }
        }

        // mark as done
        else if (message.startsWith("done")){
            int l = Integer.parseInt(message.substring(5)) - 1;
            t.get(l).markAsDone();
        }

        // new deadline
        else if (message.startsWith("deadline")){
            t.add(new Deadline(message.substring(9, message.indexOf(" /")),
                    message.substring(message.indexOf("/by") + 4)));
            acknowledgement(t.get(index), "add",index+1);
            index++;
        }

        // new todo
        else if (message.startsWith("todo")){
            t.add(new Todo(message.substring(5)));
            acknowledgement(t.get(index), "add", index+1);
            index++;
        }

        // new event
        else if (message.startsWith("event")){
            t.add(new Event(message.substring(6, message.indexOf(" /")),
                    message.substring(message.indexOf("/at") + 4)));
            acknowledgement(t.get(index), "add", index+1);
            index++;
        }

        // delete task
        else if (message.startsWith("delete")){
            int l = Integer.parseInt(message.substring(7)) - 1;
            acknowledgement(t.get(l), "del", --index);
            t.remove(t.get(l));
        }

        // throw exception
        else {
            throw new DukeException();
        }

        prepareWriteToFile(t);
        return index;
    }

    public static void acknowledgement(Task message, String note, int i){
        if (note.equals("add")) {
            System.out.println("Got it. I've added this task:");
        }
        else {
            System.out.println("Got it. I've removed this task:");
        }
        System.out.println("    " + message);
        System.out.println("Now you have " + i + (i == 1 ? " task" : " tasks") + " in your list.");
    }

    public static void prepareWriteToFile (ArrayList<Task> tasks){
        StringBuilder message = new StringBuilder();
        for (Task t :tasks) {
            message.append(t).append(System.lineSeparator());
        }
        try {
            writeToFile("data/data.txt", message.toString());
        }
        catch (IOException e){
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

    public static void writeToFile(String filePath, String message) throws IOException{
        FileWriter fw = new FileWriter(filePath);
        fw.write(message);
        fw.close();
    }


    public static void main(String[] args) {

        greet();

        ArrayList<Task> t = new ArrayList<>();
        int index = 0;
        index = initialisation(t, index);

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