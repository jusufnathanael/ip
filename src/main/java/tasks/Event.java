package tasks;

import java.time.DateTimeException;
import java.time.LocalDate;

/**
 * Represents an event in the task list.
 */
public class Event extends Task {

    protected LocalDate at;

    /**
     * Initialises the event.
     *
     * @param message full message from the user input
     * @throws DateTimeException if the date format is incorrect
     */
    public Event(String message) throws DateTimeException {
        super(message);
        this.description = message.substring(message.indexOf(' ') + 1, message.indexOf(" at: "));
        this.at = LocalDate.parse(message.substring(message.indexOf("at: ") + 4));
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " at: " + at;
    }
}
