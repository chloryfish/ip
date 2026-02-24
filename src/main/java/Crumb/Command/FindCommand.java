package Crumb.Command;

import java.util.ArrayList;

import Crumb.Storage;
import Crumb.Task.Task;
import Crumb.TaskList;
import Crumb.Ui;


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
    public void execute(TaskList tasks, Storage storage) {
        ArrayList<Task> t = tasks.findTasks(this.keyword);
        String results = tasks.formatList(t);
        Ui.showSearchResults(results);
    }
}
