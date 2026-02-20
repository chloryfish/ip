package Crumb.Command;

import Crumb.TaskList;
import Crumb.Storage;
import Crumb.Task.ToDo;
import Crumb.Ui;

import java.io.IOException;

public class CreateToDoCommand extends Command {

    protected String description;

    public CreateToDoCommand(String description) {
        this.description = description;
    }

    public void execute(TaskList tasks, Storage storage) throws IOException {
        int index = tasks.addTask(this.description);
        storage.saveData(tasks);
        Ui.showSuccessMessage("add", tasks.getShorthand(index));
    }
}
