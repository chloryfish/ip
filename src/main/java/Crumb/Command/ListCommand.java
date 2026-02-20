package Crumb.Command;

import Crumb.Storage;
import Crumb.TaskList;
import Crumb.Ui;

public class ListCommand extends Command{
    public void execute(TaskList tasks, Storage storage) {
        Ui.displayList(tasks.formatList());
    }
}
