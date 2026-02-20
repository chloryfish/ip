package Crumb.Command;

import Crumb.Task.Deadline;
import Crumb.TaskList;
import Crumb.Storage;
import Crumb.Ui;

import java.io.IOException;
import java.time.LocalDate;

public class CreateDeadlineCommand extends Command {

    protected String description;
    protected LocalDate by;

    public CreateDeadlineCommand(String description, LocalDate by) {
        this.description = description;
        this.by = by;
    }

    public void execute(TaskList tasks, Storage storage) throws IOException {
        int index = tasks.addTask(this.description, this.by);
        storage.saveData(tasks);
        Ui.showSuccessMessage("add", tasks.getShorthand(index));
    }
}
