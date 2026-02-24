package Crumb.Task;

/**
 * Task
 */
public class Task {
    protected final String description;
    protected boolean isDone;

    /**
     * Constructor for Task
     * @param description of task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "✔" : " "); // mark done task with X
    }

    public String getDescription() {
        return this.description;
    }

    /**
     * Mark this task as done
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Mark this task as not done
     */
    public void unmark() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + description;
    }

    public String toFileString(int index, String delimiter) {
        return "";
    }

    /**
     * Returns this task as readable string with index
     * Referenced by TaskList -> Ui
     * @param index of task
     * @return formatted string
     */
    public String toShorthand(int index) {
        return index + ". " + description;
    }
}

