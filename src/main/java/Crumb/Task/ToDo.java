package Crumb.Task;


/**
 * ToDo-type task
 */
public class ToDo extends Task {
    /**
     * Constructor for ToDo
     * @param description of task
     */
    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns ToDo object as a string, compatible with txt file format for Crumb.Storage
     * Referenced by Storage:saveData()
     * Format: [index]T[isDone]@@[description]
     * @param index index of this task in task list
     * @param delimiter delimiter between fields
     * @return file-format-compatible string
     */
    @Override
    public String toFileString(int index, String delimiter) {
        return index + "T" + (isDone ? '1' : '0') + delimiter + description;
    }

    /**
     * Returns this task as readable string with index
     * Referenced by TaskList -> Ui
     * @param index of task
     * @return formatted string
     */
    @Override
    public String toShorthand(int index) {
        return index + ". [T] " + description;
    }
}
