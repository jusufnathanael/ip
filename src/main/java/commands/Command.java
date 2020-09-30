package commands;

import exceptions.DukeException;
import java.io.IOException;

import storage.Storage;
import tasks.TaskList;
import ui.Ui;



public class Command {

    public static final String LIST = "list";
    public static final String DONE = "done";
    public static final String DELETE = "delete";
    public static final String DEADLINE = "deadline";
    public static final String EVENT = "event";
    public static final String TODO = "todo";
    public static final String CLEAR = "clear";
    public static final String FIND = "find";
    public static final String BYE = "bye";


    private final String command;
    private final String message;
    private boolean isExit = false;

    public Command (){
        this.command = "";
        this.message = "";
    }
    public Command (String command){
        this.command = command;
        this.message = "";
    }

    public Command (String command, String message) {
        this.command = command;
        this.message = message;
    }

    public boolean getIsExit() {
        return this.isExit;
    }


    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {

        if (command.equals(BYE)) {
            this.isExit = tasks.bye();
        } else if (command.equals(LIST)) {
            tasks.list();
        } else if (command.equals(DONE)) {
            tasks.done(message);
        } else if (command.equals(DELETE)) {
            tasks.delete(message);
        } else if (command.equals(DEADLINE)) {
            tasks.deadline(message);
            tasks.acknowledge();
        } else if (command.equals(EVENT)) {
            tasks.event(message);
            tasks.acknowledge();
        } else if (command.equals(TODO)) {
            tasks.todo(message);
            tasks.acknowledge();
        } else if (command.equals(CLEAR)) {
            tasks.clear();
        } else {
            throw new DukeException();
        }

        try {
            storage.writeToFile(tasks.getTask());
        } catch (IOException e){
            System.out.println("Something went wrong: " + e.getMessage());
        }

    }


    public void addReadFile(TaskList tasks, int done) throws DukeException {

        if (command.equals(DEADLINE)) {
            tasks.deadline(message);
        } else if (command.equals(EVENT)) {
            tasks.event(message);
        } else if (command.equals(TODO)) {
            tasks.todo(message);
        } else {
            throw new DukeException();
        }

        if (done == 1) {
            tasks.loadMarkAsDone();
        }
        tasks.printTask();
    }

}
