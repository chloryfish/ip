package Crumb.Command;

import java.io.IOException;

import Crumb.Storage;
import Crumb.TaskList;
import Crumb.UiString;


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
    public String execute(TaskList tasks, Storage storage) throws IOException {
        tasks.markTask(index);
        String msg = UiString.getSuccessMessage("mark", tasks.getShorthand(index));
        storage.saveData(tasks);
        return msg;
    }
}
