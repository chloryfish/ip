package Crumb.Command;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

import Crumb.Response;
import Crumb.Storage;
import Crumb.Task.Task;
import Crumb.TaskList;
import Crumb.UiString;
import javafx.util.Pair;

/**
 * Command that creates and adds Deadline-type task to TaskList
 */
public class CreateDeadlineCommand extends Command {

    protected String description;
    protected LocalDate by;

    /**
     * Constructor
     * Referenced by Parser
     */
    public CreateDeadlineCommand(String description, LocalDate by) {
        this.description = description;
        this.by = by;
    }

    /**
     * Executes this command
     */
    public Response execute(TaskList tasks, Storage storage) throws IOException {
        int index = tasks.addTask(this.description, this.by);
        ArrayList<Pair<Integer, Task>> tl = new ArrayList<>();
        tl.add(new Pair<>(index + 1, tasks.getTask(index)));
        storage.saveData(tasks);
        return new Response(UiString.getSuccessMessage("add"),
                tl);
    }
}
