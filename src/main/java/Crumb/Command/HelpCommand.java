package Crumb.Command;

import Crumb.Response;
import Crumb.Storage;
import Crumb.TaskList;
import Crumb.UiString;

/**
 * Command that displays instruction table
 */
public class HelpCommand extends Command {
    public Response execute(TaskList tasks, Storage storage) {
        return new Response(UiString.getHelp(), true);
    }
}
