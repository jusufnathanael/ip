package ui;

import java.io.InputStream;
import java.io.PrintStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

import tasks.Task;
import commands.Command;

/**
 * Text UI of the application.
 */
public class Ui {

    /** A break line to separate commands and outputs */
    public final String DIVIDER = "-------------------------------------------" +
            "--------------------------------------";

    /** A line separator */
    private static final String BREAK = System.lineSeparator();

    /** Duke main logo */
    private static final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    private final Scanner in;
    private final PrintStream out;


    public Ui() {
        this(System.in, System.out);
    }

    public Ui(InputStream in, PrintStream out) {
        this.in = new Scanner(in);
        this.out = out;
    }

    public void printLine() {
        System.out.println(DIVIDER);
    }

    /**
     * Generates and prints the welcome message upon the start of the application.
     */
    public void printWelcomeMessage() {
        System.out.println(LOGO + BREAK + "Hello! I'm Duke\n" + "What can I do for you?\n" + DIVIDER);
    }

    /**
     * Generates and prints the goodbye message at the end of the application.
     */
    public void printByeMessage(){
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Generates a message whenever a task is marked as done.
     *
     * @param icon tick or cross icon
     * @param description description of the task
     */
    public void printDoneMessage(String icon, String description) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("[" + icon + "] " + description);
    }

    /**
     * Generates a message whenever a task is added or deleted.
     *
     * @param message task description
     * @param note add or delete method
     * @param i total items in the list
     */
    public void acknowledge(Task message, String note, int i) {
        if (note.equals("add")) {
            System.out.println("Got it. I've added this task:");
        } else {
            System.out.println("Got it. I've removed this task:");
        }
        System.out.println("    " + message);
        System.out.println("Now you have " + i + (i == 1 ? " task" : " tasks") + " in your list.");
    }

    /**
     * Prints all the tasks inside the current list.
     *
     * @param t list of task to be printed
     */
    public void printList(ArrayList<Task> t) {
        if (t.size() == 0){
            System.out.println("Currently there are no tasks in the list.");
        } else {
            for (int i = 0; i < t.size(); i++) {
                System.out.print(i + 1 + ". ");
                System.out.println(t.get(i));
            }
        }
    }

    /**
     * Find an item according to the input keyword.
     *
     * @param tasks current list of tasks
     * @param keyword keyword to be found in the task list
     */
    public void findItem(ArrayList<Task> tasks, String keyword) {
        ArrayList<Task> result = new ArrayList<>();
        int count = 0;
        for (Task t: tasks) {
            if (t.toString().contains(keyword)) {
                result.add(t);
                count++;
            }
        }
        if (count == 0) {
            System.out.println("Sorry, we found no item with \"" + keyword + "\" in the list.");
        } else {
            System.out.println("We found " + count + " item in the list:");
            this.printList(result);
        }
    }

    /**
     * Prints today's date.
     */
    public void printCurrentDate() {
        System.out.println("Current Date: " + LocalDate.now() + BREAK + DIVIDER);
    }

    /**
     * Generates and prints the message for DukeException from the main class Duke.
     * Suggests the correct input format to the user.
     *
     * @param command type of task: deadline/event/todo
     */
    public void printCorrectFormat(String command) {
        if (command.equals(Command.DEADLINE)) {
            System.out.println("OOPS!!! Please use this format: " +
                    "deadline <description> /by YYYY-MM-DD");
        } else if (command.equals(Command.EVENT)) {
            System.out.println("OOPS!!! Please use this format: " +
                    "event <description> /at YYYY-MM-DD");
        } else if (command.equals(Command.TODO)){
            System.out.println("OOPS!!! Please use this format: " +
                    "todo <description>");
        }
    }
}
