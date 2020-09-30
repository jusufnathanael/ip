package tasks;

/**
 * Represents a general task in the task list.
 */
public class Task {

    protected String description;
    protected boolean isDone;

    /**
     * Initialises the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Get tick or x symbols
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    /**
     * Return whether a task is already done or not in integers
     */
    public int getIsDone() {
        return this.isDone ? 1 : 0;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public String getDescription() {
        return this.description;
    }

    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
