package Crumb.Command;

import java.io.IOException;

import Crumb.Storage;
import Crumb.TaskList;
import Crumb.Ui;


/**
 * Command that marks a task as done
 * Identified by index (0-based)
 */
public class MarkCommand extends Command {

    protected int index;

    /**
     * Constructor
     * Referenced by Parser
     */
    public MarkCommand(int index) {
        this.index = index;
    }

    /**
     * Executes this command
     */
    public void execute(TaskList tasks, Storage storage) throws IOException {
        tasks.markTask(index);
        Ui.showSuccessMessage("mark", tasks.getShorthand(index));
        storage.saveData(tasks);
    }
}
