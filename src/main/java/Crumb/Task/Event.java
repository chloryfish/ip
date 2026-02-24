package Crumb.Task;

import java.time.LocalDate;

import Crumb.Parser;

/**
 * Event-type task
 */
public class Event extends Task {

    /**
     * Start date
     */
    protected final LocalDate from;
    /**
     * End date
     */
    protected final LocalDate to;

    /**
     * Constructor for Event
     * @param description of task
     * @param from start date
     * @param to end date
     */
    public Event(String description, LocalDate from, LocalDate to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: "
                + Parser.formatDateReadable(from) + " to: "
                + Parser.formatDateReadable(to) + ")";
    }

    /**
     * Returns this task as readable string with index
     * Referenced by TaskList -> Ui
     * @param index of task
     * @return formatted string
     */
    @Override
    public String toShorthand(int index) {
        return index + ". [E] " + description + " (from: "
                + Parser.formatDateReadable(from) + " to: "
                + Parser.formatDateReadable(to) + ")";
    }

    /**
     * Returns Event object as a string, compatible with txt file format for Crumb.Storage
     * Referenced by Storage:saveData()
     * Format: [index]E[isDone]@@[description]@@[from]@@[to]
     * @param index index of this task in task list
     * @param delimiter delimiter between fields
     * @return file-format-compatible string
     */
    @Override
    public String toFileString(int index, String delimiter) {
        return index + "E" + (isDone ? '1' : '0') + delimiter + description
                + delimiter + from
                + delimiter + to;
    }
}
