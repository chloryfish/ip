import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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
                + Crumb.formatDateReadable(from) + " to: "
                + Crumb.formatDateReadable(to) + ")";
    }

    @Override
    public String toFileString(int index) {
        return index + "E" + (isDone ? '1' : '0') + Crumb.DELIMITER + description
                + Crumb.DELIMITER + from
                + Crumb.DELIMITER + to;
    }
}
