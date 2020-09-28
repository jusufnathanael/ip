package tasks;

public class Todo extends Task {

    public Todo(String message) {
        super(message);
        this.description = message.substring(message.indexOf(' ') + 1);
    }

    public String toString() {
        return "[T]" + super.toString();
    }
}
