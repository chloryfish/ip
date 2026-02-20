package Crumb.Command;

import Crumb.Storage;
import Crumb.Task.Task;
import Crumb.TaskList;
import Crumb.Ui;

import java.io.IOException;

public class MarkCommand extends Command{

    protected int index;

    public MarkCommand(int index) {
        this.index = index;
    }

    public void execute(TaskList tasks, Storage storage) throws IOException {
        tasks.markTask(index);
        Ui.showSuccessMessage("mark", tasks.getShorthand(index));
        storage.saveData(tasks);
    }
}
