package Crumb.Command;

import Crumb.Storage;
import Crumb.Task.Task;
import Crumb.TaskList;
import Crumb.Ui;

import java.io.IOException;

public class UnmarkCommand extends Command{

    protected int index;

    public UnmarkCommand(int index) {
        this.index = index;
    }

    public void execute(TaskList tasks, Storage storage) throws IOException {
        tasks.unMarkTask(index);
        Ui.showSuccessMessage("unmark", tasks.getShorthand(index));
        storage.saveData(tasks);
    }
}
