import org.w3c.dom.css.DOMImplementationCSS;
import tasks.Task;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;


public class Ui {

    private static final String DIVIDER = "-------------------------------------------------------";
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

    public void printWelcomeMessage(){
        System.out.println(LOGO + BREAK + "Hello! I'm Duke\n" + "What can I do for you?\n" + DIVIDER);
    }

    public void printByeMessage(){
        System.out.println(DIVIDER + BREAK + "Bye. Hope to see you again soon!\n" + DIVIDER);
    }

    public void printDoneMessage(String icon, String description) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("[" + icon + "] " + description);
    }

    public void acknowledge(Task message, String note, int i){
        if (note.equals("add")) {
            System.out.println("Got it. I've added this task:");
        } else {
            System.out.println("Got it. I've removed this task:");
        }
        System.out.println("    " + message);
        System.out.println("Now you have " + i + (i == 1 ? " task" : " tasks") + " in your list.");
    }

    public void printList(ArrayList<Task> t){
        for (int i = 0; i < t.size(); i++) {
            System.out.print(i + 1 + ". ");
            System.out.println(t.get(i));
        }
    }

}
