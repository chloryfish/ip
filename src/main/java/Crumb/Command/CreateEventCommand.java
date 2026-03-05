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
 * Command that creates and adds Event-type task to TaskList
 */
public class CreateEventCommand extends Command {

    protected String description;
    protected LocalDate from;
    protected LocalDate to;

    /**
     * Constructor
     * Referenced by Parser
     */
    public CreateEventCommand(String description, LocalDate from, LocalDate to) {
        this.description = description;
        this.from = from;
        this.to = to;
    }

    /**
     * Executes this command
     */
    public Response execute(TaskList tasks, Storage storage) throws IOException {
        int index = tasks.addTask(this.description, this.from, this.to);
        ArrayList<Pair<Integer, Task>> tl = new ArrayList<>();
        tl.add(new Pair<>(index + 1, tasks.getTask(index)));
        storage.saveData(tasks);
        return new Response(UiString.getSuccessMessage("add"),
                tl);
    }
}
