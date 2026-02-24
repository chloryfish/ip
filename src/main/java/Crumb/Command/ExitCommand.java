package Crumb.Command;

import Crumb.Storage;
import Crumb.TaskList;
import Crumb.Ui;

/**
 * Command that quits app
 */
public class ExitCommand extends Command {
    /**
     * Executes this command
     */
    public void execute(TaskList tasks, Storage storage) {
        Ui.showGoodbye();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
