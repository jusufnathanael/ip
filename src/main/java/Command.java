import java.io.IOException;
import java.util.ArrayList;

import tasks.Task;
import tasks.Deadline;
import tasks.Event;
import tasks.Todo;


public class Command {

    private final String command;
    private final String message;
    private boolean isExit = false;

    public Command (String message) {
        String[] words = message.split(" ");
        command = words[0];
        this.message = message;
    }

    public boolean getIsExit() {
        return this.isExit;
    }
    public void execute(ArrayList<Task> t, Ui ui, Storage storage) throws DukeException {

        if (command.equals("bye")) {
            this.isExit = true;
            ui.printByeMessage();
        } else if (command.equals("list")) {
            ui.printList(t);
        } else if (command.equals("done")) {
            int i = Integer.parseInt(message.substring(5)) - 1;
            t.get(i).markAsDone();
            ui.printDoneMessage(t.get(i).getStatusIcon(), t.get(i).getDescription());
        } else if (command.equals("delete")) {
            int l = Integer.parseInt(message.substring(7)) - 1;
            ui.acknowledge(t.get(l), "del", t.size() - 1);
            t.remove(t.get(l));
        } else if (command.equals("deadline")) {
            t.add(new Deadline(message));
            ui.acknowledge(t.get(t.size()-1), "add", t.size());
        } else if (command.equals("event")) {
            t.add(new Event(message));
            ui.acknowledge(t.get(t.size()-1), "add", t.size());
        } else if (command.equals("todo")) {
            t.add(new Todo(message));
            ui.acknowledge(t.get(t.size()-1), "add", t.size());
        } else {
            throw new DukeException();
        }

        try {
            storage.writeToFile(t);
        } catch (IOException e){
            System.out.println("Something went wrong: " + e.getMessage());
        }

    }

    public void addReadFile(ArrayList<Task> t, int done) throws DukeException {
        Task task;
        if (command.equals("deadline")) {
            task = new Deadline(message);
        } else if (command.equals("event")) {
            task = new Event(message);
        } else if (command.equals("todo")) {
            task = new Todo(message);
        } else {
            throw new DukeException();
        }
        if (done == 1) {
            task.markAsDone();
        }
        t.add(task);
        System.out.println(task);
    }

}
