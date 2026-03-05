package Crumb.Command;

import java.io.IOException;
import java.util.ArrayList;

import Crumb.Response;
import Crumb.Storage;
import Crumb.Task.Task;
import Crumb.TaskList;
import Crumb.UiString;
import javafx.util.Pair;


/**
 * Command that creates and adds ToDo-type task to TaskList
 */
public class CreateToDoCommand extends Command {

    protected String description;

    /**
     * Constructor
     * Referenced by Parser
     */
    public CreateToDoCommand(String description) {
        this.description = description;
    }

    /**
     * Executes this command
     */
    public Response execute(TaskList tasks, Storage storage) throws IOException {
        int index = tasks.addTask(this.description);
        ArrayList<Pair<Integer, Task>> tl = new ArrayList<>();
        tl.add(new Pair<>(index + 1, tasks.getTask(index)));
        storage.saveData(tasks);
        return new Response(UiString.getSuccessMessage("add"),
                tl);
    }
}
