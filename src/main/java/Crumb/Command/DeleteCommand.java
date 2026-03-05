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
 * Command that deletes a task
 * Identified by index (0-based)
 */
public class DeleteCommand extends Command {

    protected int index; // actual index

    /**
     * Constructor
     * Referenced by Parser
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Executes this command
     */
    public Response execute(TaskList tasks, Storage storage) throws IOException {
        ArrayList<Pair<Integer, Task>> tl = new ArrayList<>();
        tl.add(new Pair<>(index + 1, tasks.getTask(this.index)));
        tasks.deleteTask(index);
        storage.saveData(tasks);
        return new Response(UiString.getSuccessMessage("delete"),
                tl);
    }
}
