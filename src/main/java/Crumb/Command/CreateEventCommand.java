package Crumb.Command;

import java.io.IOException;
import java.time.LocalDate;

import Crumb.Storage;
import Crumb.TaskList;
import Crumb.UiString;


/**
 * Command that creates and adds Event-type task to TaskList
 */
public class CreateEventCommand extends Command {

    protected String description;
    protected LocalDate from;
    protected LocalDate to;

    /**
     * Constructor
     * Referenced by Parser
     */
    public CreateEventCommand(String description, LocalDate from, LocalDate to) {
        this.description = description;
        this.from = from;
        this.to = to;
    }

    /**
     * Executes this command
     */
    public String execute(TaskList tasks, Storage storage) throws IOException {
        int index = tasks.addTask(this.description, this.from, this.to);
        storage.saveData(tasks);
        return UiString.getSuccessMessage("add", tasks.getShorthand(index));
    }
}
