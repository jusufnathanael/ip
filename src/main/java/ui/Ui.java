package ui;

import java.io.InputStream;
import java.io.PrintStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

import tasks.Task;
import commands.Command;


public class Ui {

    public final String DIVIDER = "-------------------------------------------" +
            "--------------------------------------";
    private static final String BREAK = System.lineSeparator();
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

    public void printWelcomeMessage() {
        System.out.println(LOGO + BREAK + "Hello! I'm Duke\n" + "What can I do for you?\n" + DIVIDER);
    }

    public void printByeMessage(){
        System.out.println("Bye. Hope to see you again soon!");
    }

    public void printDoneMessage(String icon, String description) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("[" + icon + "] " + description);
    }

    public void acknowledge(Task message, String note, int i) {
        if (note.equals("add")) {
            System.out.println("Got it. I've added this task:");
        } else {
            System.out.println("Got it. I've removed this task:");
        }
        System.out.println("    " + message);
        System.out.println("Now you have " + i + (i == 1 ? " task" : " tasks") + " in your list.");
    }

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

    public void printCurrentDate() {
        System.out.println("Current Date: " + LocalDate.now() + BREAK + DIVIDER);
    }

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
