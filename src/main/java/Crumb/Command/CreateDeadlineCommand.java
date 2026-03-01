package Crumb.Command;

import java.io.IOException;
import java.time.LocalDate;

import Crumb.Storage;
import Crumb.TaskList;
import Crumb.UiString;

/**
 * Command that creates and adds Deadline-type task to TaskList
 */
public class CreateDeadlineCommand extends Command {

    protected String description;
    protected LocalDate by;

    /**
     * Constructor
     * Referenced by Parser
     */
    public CreateDeadlineCommand(String description, LocalDate by) {
        this.description = description;
        this.by = by;
    }

    /**
     * Executes this command
     */
    public String execute(TaskList tasks, Storage storage) throws IOException {
        int index = tasks.addTask(this.description, this.by);
        storage.saveData(tasks);
        return UiString.getSuccessMessage("add", tasks.getShorthand(index));
    }
}
