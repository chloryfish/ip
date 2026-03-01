package Crumb.Command;

import Crumb.Storage;
import Crumb.TaskList;
import Crumb.UiString;

/**
 * Command that displays formatted list of tasks
 */
public class ListCommand extends Command {
    public String execute(TaskList tasks, Storage storage) {
        return UiString.getList(tasks.formatList());
    }
}
