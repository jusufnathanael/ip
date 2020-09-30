package tasks;

import java.time.DateTimeException;
import java.time.LocalDate;


public class Deadline extends Task {

    protected LocalDate by;

    public Deadline(String message) throws DateTimeException {
        super(message);
        this.description = message.substring(message.indexOf(' ') + 1, message.indexOf(" /by "));
        this.by = LocalDate.parse(message.substring(message.indexOf("/by ") + 4));
    }

    public String toString() {
        return "[D]" + super.toString() + " /by " + by;
    }
}
