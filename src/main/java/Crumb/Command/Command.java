package Crumb.Command;

import java.io.IOException;

import Crumb.Storage;
import Crumb.TaskList;


/**
 * Command
 */
public abstract class Command {
    public abstract String execute(TaskList tasks, Storage storage) throws IOException;

    public boolean isExit() {
        return false;
    }
}
