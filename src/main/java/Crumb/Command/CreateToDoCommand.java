package Crumb.Command;

import java.io.IOException;

import Crumb.Storage;
import Crumb.TaskList;
import Crumb.Ui;


/**
 * Command that creates and adds ToDo-type task to TaskList
 */
public class CreateToDoCommand extends Command {

    protected String description;

    /**
     * Constructor
     * Referenced by Parser
     */
    public CreateToDoCommand(String description) {
        this.description = description;
    }

    /**
     * Executes this command
     */
    public void execute(TaskList tasks, Storage storage) throws IOException {
        int index = tasks.addTask(this.description);
        storage.saveData(tasks);
        Ui.showSuccessMessage("add", tasks.getShorthand(index));
    }
}
