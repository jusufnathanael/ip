package tasks;

import java.util.ArrayList;

import exceptions.DukeException;
import ui.Ui;


/**
 * Represents the entire task list. Contains the data of each task.
 */
public class TaskList {

    private final ArrayList<Task> tasks;
    private final Ui ui = new Ui();

    public TaskList(){
        this.tasks = new ArrayList<Task>();
    }

    public TaskList(ArrayList<Task> t) {
        this.tasks = t;
    }

    public ArrayList<Task> getTask(){
        return this.tasks;
    }

    public void delete(String message) throws NumberFormatException {
        if (message.length() < 8) {
            throw new NumberFormatException();
        }
        int l = Integer.parseInt(message.substring(7)) - 1;
        ui.acknowledge(tasks.get(l), "del", tasks.size() - 1);
        tasks.remove(tasks.get(l));
    }

    public void clear() {
        tasks.clear();
        ui.printClear();
    }

    public void list() {
        ui.printList(tasks);
    }

    public void done(String message) throws DukeException, NumberFormatException {
        if (message.length() < 6) {
            throw new NumberFormatException();
        }
        int i = Integer.parseInt(message.substring(5)) - 1;
        if (!tasks.get(i).isDone) {
            tasks.get(i).markAsDone();
            ui.printDoneMessage(tasks.get(i).getStatusIcon(), tasks.get(i).getDescription());
        }
        else {
            throw new DukeException();
        }
    }

    public void deadline(String message) {
        tasks.add(new Deadline(message));
    }

    public void event(String message) {
        tasks.add(new Event(message));
    }

    public void todo(String message) {
        tasks.add(new Todo(message));
    }

    public void find(String message) {
        String keyword = message.substring(5);
        ui.findItem(tasks, keyword);
    }

    public void acknowledge() {
        ui.acknowledge(tasks.get(tasks.size() - 1), "add", tasks.size());
    }

    public void loadMarkAsDone() {
        tasks.get(tasks.size() - 1).markAsDone();
    }

    public boolean bye() {
        ui.printByeMessage();
        return true;
    }

    public void printTask() {
        System.out.println(tasks.get(tasks.size() - 1));
    }

}
