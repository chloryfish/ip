package Crumb.Command;

import Crumb.Storage;
import Crumb.Task.Task;
import Crumb.TaskList;
import Crumb.Ui;

import java.io.IOException;

public class DeleteCommand extends Command{

    protected int index; // actual index

    public DeleteCommand(int index) {
        this.index = index;
    }

    public void execute(TaskList tasks, Storage storage) throws IOException {
        String s = tasks.getShorthand(index);
        tasks.deleteTask(index);
        storage.saveData(tasks);
        Ui.showSuccessMessage("delete", s);
    }
}
