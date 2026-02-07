import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    protected final LocalDate by;

    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString()
                + " (by: " + Crumb.formatDateReadable(by) + ")";
    }

    @Override
    public String toFileString(int index) {
        return index + "D" + (isDone ? '1' : '0') + Crumb.DELIMITER + description
                + Crumb.DELIMITER + by;
    }

}
