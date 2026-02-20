package Crumb.Command;

import Crumb.Storage;
import Crumb.TaskList;
import Crumb.Ui;

public class ExitCommand extends Command{
    public void execute(TaskList tasks, Storage storage) {
        Ui.showGoodbye();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
