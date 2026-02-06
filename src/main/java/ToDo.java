public class ToDo extends Task {
    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String toFileString(int index) {
        return index + "T" + (isDone ? '1' : '0') + Crumb.DELIMITER + description;
    }
}
