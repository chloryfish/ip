package Crumb.Task;

import Crumb.Parser;

import java.time.LocalDate;

public class Deadline extends Task {

    protected final LocalDate by;

    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString()
                + " (by: " + Parser.formatDateReadable(by) + ")";
    }

    @Override
    public String toShorthand(int index) {
        return index + ". [D] " + description
                + " (by: " + Parser.formatDateReadable(by) + ")";
    }

    @Override
    public String toFileString(int index, String delimiter) {
        return index + "D" + (isDone ? '1' : '0') + delimiter + description
                + delimiter + by;
    }

}
