package tasks;

import java.time.DateTimeException;
import java.time.LocalDate;

/**
 * Represents a deadline in the task list.
 */
public class Deadline extends Task {

    protected LocalDate by;

    /**
     * Initialises the deadline.
     *
     * @param message full message from the user input
     * @throws DateTimeException if the date format is incorrect
     */
    public Deadline(String message) throws DateTimeException {
        super(message);
        this.description = message.substring(message.indexOf(' ') + 1, message.indexOf(" by: "));
        this.by = LocalDate.parse(message.substring(message.indexOf("by: ") + 4));
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " by: " + by;
    }
}
