package tasks;

public class Event extends Task {

    protected String at;

    public Event(String message) {
        super(message);
        this.description = message.substring(message.indexOf(' ') + 1, message.indexOf(" /at"));
        this.at = message.substring(message.indexOf("/at") + 4);
    }

    public String toString() {
        return "[E]" + super.toString() + " /at " + at;
    }
}
