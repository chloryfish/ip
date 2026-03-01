package Crumb.Command;

import Crumb.Storage;
import Crumb.TaskList;
import Crumb.UiString;

/**
 * Command that displays instruction table
 */
public class HelpCommand extends Command {
    public String execute(TaskList tasks, Storage storage) {
        return UiString.getInstructionTable();
    }
}
