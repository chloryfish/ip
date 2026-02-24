package Crumb.Command;

import java.io.IOException;

import Crumb.Storage;
import Crumb.TaskList;
import Crumb.Ui;

/**
 * Command that marks a task as not done
 * Identified by index (0-based)
 */
public class UnmarkCommand extends Command {

    protected int index;

    /**
     * Constructor
     * Referenced by Parser
     */
    public UnmarkCommand(int index) {
        this.index = index;
    }

    /**
     * Executes this command
     */
    public void execute(TaskList tasks, Storage storage) throws IOException {
        tasks.unMarkTask(index);
        Ui.showSuccessMessage("unmark", tasks.getShorthand(index));
        storage.saveData(tasks);
    }
}
