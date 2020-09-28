import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import tasks.Task;
import tasks.Deadline;
import tasks.Event;
import tasks.Todo;


public class Duke {

    private static Ui ui;
    private static Storage storage;

    private static void initialisation(ArrayList<Task> t){

        try {
            storage = new Storage("data/data.txt");
            storage.readFile(t);
        }
        catch (InvalidFilePathException | FileNotFoundException e){
            System.out.println("File not found");
        }
    }


    public static void main(String[] args) {
        //run();
        ui = new Ui();
        ui.printWelcomeMessage();

        ArrayList<Task> t = new ArrayList<Task>();
        initialisation(t);

        Scanner in = new Scanner(System.in);
        String message = in.nextLine();

        try {
            storage = new Storage("data/data.txt");
        }
        catch (InvalidFilePathException e){
            System.out.println(e.getMessage());
        }
        // main loop
        while (!message.equals("bye")){
            ui.printLine();

            try {
                Command c = new Command(message);
                c.execute(t, ui, storage);
            }
            catch (DukeException e){
                System.out.println("OOPS!!! I'm sorry, but I don't know what that means.");
            }
            catch (StringIndexOutOfBoundsException e){
                System.out.println("OOPS!!! The description of a " + message + " cannot be empty.");
            }
            catch (IndexOutOfBoundsException e){
                System.out.println("Index out of bound");
            }
            catch (NumberFormatException e){
                System.out.println(e);
            }
            ui.printLine();
            message = in.nextLine();
        }

        ui.printByeMessage();
    }
}