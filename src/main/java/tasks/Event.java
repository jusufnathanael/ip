package tasks;

import java.time.DateTimeException;
import java.time.LocalDate;


public class Event extends Task {

    protected LocalDate at;

    public Event(String message) throws DateTimeException {
        super(message);
        this.description = message.substring(message.indexOf(' ') + 1, message.indexOf(" /at "));
        this.at = LocalDate.parse(message.substring(message.indexOf("/at ") + 4));
    }

    public String toString() {
        return "[E]" + super.toString() + " /at " + at;
    }
}
