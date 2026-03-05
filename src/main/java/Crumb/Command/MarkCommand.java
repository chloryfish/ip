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
 * Command that marks a task as done
 * Identified by index (0-based)
 */
public class MarkCommand extends Command {

    protected int index;

    /**
     * Constructor
     * Referenced by Parser
     */
    public MarkCommand(int index) {
        this.index = index;
    }

    /**
     * Executes this command
     */
    public Response execute(TaskList tasks, Storage storage) throws IOException {
        tasks.markTask(index);
        ArrayList<Pair<Integer, Task>> tl = new ArrayList<>();
        tl.add(new Pair<>(index + 1, tasks.getTask(index)));
        String msg = UiString.getSuccessMessage("mark");
        storage.saveData(tasks);
        return new Response(msg, tl);
    }
}
