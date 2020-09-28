package tasks;

import java.util.ArrayList;

public class TaskList {

    private final ArrayList<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<Task>();
    }

    public void add(Task newTask){
        tasks.add(newTask);
    }

    public void delete(int index){
        tasks.remove(index);
    }

    public void clear() {
        tasks.clear();
    }

    public ArrayList<Task> getList() {
        return tasks;
    }

}
