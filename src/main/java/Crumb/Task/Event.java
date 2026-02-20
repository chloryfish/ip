package Crumb.Task;

import Crumb.Parser;

import java.time.LocalDate;

public class Event extends Task {

    protected LocalDate from;
    protected LocalDate to;

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

    @Override
    public String toShorthand(int index) {
        return index + ". [E] " + description + " (from: "
                + Parser.formatDateReadable(from) + " to: "
                + Parser.formatDateReadable(to) + ")";
    }

    @Override
    public String toFileString(int index, String delimiter) {
        return index + "E" + (isDone ? '1' : '0') + delimiter + description
                + delimiter + from
                + delimiter + to;
    }
}
