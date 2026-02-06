public class Deadline extends Task {

    protected final String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    @Override
    public String toFileString(int index) {
        return index + "D" + (isDone ? '1' : '0') + Crumb.DELIMITER + description
                + Crumb.DELIMITER + by;
    }

}
