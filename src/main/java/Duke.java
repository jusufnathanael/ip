import java.util.Scanner;
import java.util.ArrayList;
import java.io.FileNotFoundException;

import tasks.Task;


public class Duke {

    private static Ui ui;
    private static Storage storage;
    private final ArrayList<Task> tasks;

    public Duke(String filePath) {
        ui = new Ui();
        ui.printWelcomeMessage();
        try {
            storage = new Storage(filePath);
        }
        catch (InvalidFilePathException e) {
            System.out.println("File does not end with .txt");
        }
        tasks = new ArrayList<Task>();
        try {
            storage.readFile(tasks);
        }
        catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }


    public void run() {

        boolean isExit = false;
        Scanner in = new Scanner(System.in);

        while (!isExit) {
            String message = in.nextLine();
            ui.printLine();
            try {
                Command c = new Command(message);
                c.execute(tasks, ui, storage);
                isExit = c.getIsExit();
            } catch (DukeException e) {
                System.out.println("OOPS!!! I'm sorry, but I don't know what that means.");
            } catch (StringIndexOutOfBoundsException e) {
                System.out.println("OOPS!!! The description of a " + message + " cannot be empty.");
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Index out of bound!");
            } catch (NumberFormatException e) {
                System.out.println("Wrong number format!");
            }
            ui.printLine();
        }

    }

    public static void main(String[] args) {
        new Duke("data/data.txt").run();
    }
}