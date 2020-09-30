package tasks;

public class Todo extends Task {

    public Todo(String message) throws StringIndexOutOfBoundsException {
        super(message);
        this.description = message.substring(message.indexOf("todo ") + 5);
        if (this.description.length() == 0) {
            throw new StringIndexOutOfBoundsException();
        }
    }

    public String toString() {
        return "[T]" + super.toString();
    }
}
