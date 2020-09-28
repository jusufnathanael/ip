package tasks;

public class Deadline extends Task {

    protected String by;

    public Deadline(String message) {
        super(message);
        this.description = message.substring(message.indexOf(' ') + 1, message.indexOf(" /"));
        this.by = message.substring(message.indexOf("/by") + 4);
    }

    public String toString() {
        return "[D]" + super.toString() + " /by " + by;
    }
}
