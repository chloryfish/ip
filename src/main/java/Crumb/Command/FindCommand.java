package Crumb.Command;

import Crumb.Storage;
import Crumb.Task.Task;
import Crumb.TaskList;
import Crumb.Ui;

import java.util.ArrayList;

public class FindCommand extends Command{

    protected String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }
    public void execute(TaskList tasks, Storage storage) {
        ArrayList<Task> t = tasks.findTasks(this.keyword);
        String results = tasks.formatList(t);
        Ui.showSearchResults(results);
    }
}
