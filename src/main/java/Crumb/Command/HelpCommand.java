package Crumb.Command;

import Crumb.Storage;
import Crumb.TaskList;
import Crumb.Ui;

/**
 * Command that displays instruction table
 */
public class HelpCommand extends Command {
    public void execute(TaskList tasks, Storage storage) {
        Ui.showInstructionTable();
    }
}
