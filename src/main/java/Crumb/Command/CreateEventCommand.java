package Crumb.Command;

import Crumb.Task.Event;
import Crumb.TaskList;
import Crumb.Storage;
import Crumb.Ui;

import java.io.IOException;
import java.time.LocalDate;

public class CreateEventCommand extends Command {

    protected String description;
    protected LocalDate from;
    protected  LocalDate to;

    public CreateEventCommand(String description, LocalDate from, LocalDate to) {
        this.description = description;
        this.from = from;
        this.to = to;
    }

    public void execute(TaskList tasks, Storage storage) throws IOException {
        int index = tasks.addTask(this.description, this.from, this.to);
        storage.saveData(tasks);
        Ui.showSuccessMessage("add", tasks.getShorthand(index));
    }
}
