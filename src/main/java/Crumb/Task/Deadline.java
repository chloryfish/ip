package Crumb.Task;

import java.time.LocalDate;

import Crumb.Parser;


/**
 * Deadline-type task
 */
public class Deadline extends Task {

    /**
     * Deadline
     */
    protected final LocalDate by;
    /**
     * Constructor for Deadline
     * @param description of task
     * @param by deadline
     */
    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString()
                + " (by: " + Parser.formatDateReadable(by) + ")";
    }

    /**
     * Returns this task as readable string with index
     * Referenced by TaskList -> Ui
     * @param index of task
     * @return formatted string
     */
    @Override
    public String toShorthand(int index) {
        return index + ". [D] " + description
                + " (by: " + Parser.formatDateReadable(by) + ")";
    }

    /**
     * Returns Deadline object as a string, compatible with txt file format for Crumb.Storage
     * Referenced by Storage:saveData()
     * Format: [index]D[isDone]@@[description]@@[by]
     * @param index index of this task in task list
     * @param delimiter delimiter between fields
     * @return file-format-compatible string
     */
    @Override
    public String toFileString(int index, String delimiter) {
        return index + "D" + (isDone ? '1' : '0') + delimiter + description
                + delimiter + by;
    }

}
