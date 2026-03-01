package Crumb.Command;

import Crumb.Storage;
import Crumb.TaskList;
import Crumb.UiString;

/**
 * Command that quits app
 */
public class ExitCommand extends Command {
    /**
     * Executes this command
     */
    public String execute(TaskList tasks, Storage storage) {
        return UiString.getGoodbye();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
