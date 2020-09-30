package tasks;

/**
 * Represents a to do item in the task list.
 */
public class Todo extends Task {

    /**
     * Initialises the todo.
     *
     * @param message full message from the user input
     * @throws StringIndexOutOfBoundsException if there is no description
     */
    public Todo(String message) throws StringIndexOutOfBoundsException {
        super(message);
        this.description = message.substring(message.indexOf("todo ") + 5);
        if (this.description.length() == 0) {
            throw new StringIndexOutOfBoundsException();
        }
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
