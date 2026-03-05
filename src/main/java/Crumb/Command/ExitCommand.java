package Crumb.Command;

import Crumb.Response;
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
    public Response execute(TaskList tasks, Storage storage) {
        return new Response(UiString.getGoodbye());
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
