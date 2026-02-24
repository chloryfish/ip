package Crumb.Command;

import java.io.IOException;

import Crumb.Storage;
import Crumb.TaskList;
import Crumb.Ui;


/**
 * Command that deletes a task
 * Identified by index (0-based)
 */
public class DeleteCommand extends Command {

    protected int index; // actual index

    /**
     * Constructor
     * Referenced by Parser
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Executes this command
     */
    public void execute(TaskList tasks, Storage storage) throws IOException {
        String s = tasks.getShorthand(index);
        tasks.deleteTask(index);
        storage.saveData(tasks);
        Ui.showSuccessMessage("delete", s);
    }
}
