import java.util.Scanner;
import java.io.FileNotFoundException;

import exceptions.DukeException;
import exceptions.InvalidFilePathException;

import commands.Command;
import parser.Parser;
import storage.Storage;
import tasks.TaskList;
import ui.Ui;


public class Duke {

    private static Ui ui;
    private static Storage storage;
    private static TaskList tasks;


    public Duke(String filePath) {
        ui = new Ui();
        ui.printCurrentDate();
        ui.printWelcomeMessage();
        try {
            storage = new Storage(filePath);
        } catch (InvalidFilePathException e) {
            System.out.println("File does not end with .txt");
        }
        try {
            tasks = new TaskList(storage.load());
        } catch (FileNotFoundException e) {
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
                Parser p = new Parser(message);
                Command c = p.parseCommand();
                c.execute(tasks, ui, storage);
                isExit = c.getIsExit();
            } catch (DukeException e) {
                System.out.println("OOPS!!! I'm sorry, but I don't know what that means.");
            } catch (StringIndexOutOfBoundsException e) {
                ui.printCorrectFormat(new Parser(message).getCommand());
            } catch (IndexOutOfBoundsException e) {
                System.out.println("OOPS!!! The input index is out of bound.");
            } catch (NumberFormatException e) {
                System.out.println("OOPS!!! The input should be a number.");
            }
            ui.printLine();
        }

    }

    public static void main(String[] args) {
        new Duke("data/data.txt").run();
    }
}