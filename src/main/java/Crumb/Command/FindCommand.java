package Crumb.Command;

import java.util.ArrayList;

import Crumb.Response;
import Crumb.Storage;
import Crumb.Task.Task;
import Crumb.TaskList;
import Crumb.UiString;
import javafx.util.Pair;


/**
 * Command that finds tasks that contain a certain keyword in their description
 */
public class FindCommand extends Command {

    protected String keyword;

    /**
     * Constructor
     * Referenced by Parser
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }
    /**
     * Executes this command
     */
    public Response execute(TaskList tasks, Storage storage) {
        ArrayList<Pair<Integer, Task>> t = tasks.findTasks(this.keyword);
        return new Response(UiString.getFindMessage(), t);
    }
}
