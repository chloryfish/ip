package Crumb.Task;

import Crumb.Parser;

public class ToDo extends Task {
    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String toFileString(int index, String delimiter) {
        return index + "T" + (isDone ? '1' : '0') + delimiter + description;
    }

    @Override
    public String toShorthand(int index) {
        return index + ". [T] " + description;
    }
}
