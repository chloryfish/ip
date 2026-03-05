package Crumb.Command;

import Crumb.Response;
import Crumb.Storage;
import Crumb.TaskList;
import Crumb.UiString;

/**
 * Command that displays formatted list of tasks
 */
public class ListCommand extends Command {
    /**
     * Executes this command
     */
    public Response execute(TaskList tasks, Storage storage) {
        return new Response(UiString.getListMessage(),
                tasks.getIndexedTasks());
    }
}
