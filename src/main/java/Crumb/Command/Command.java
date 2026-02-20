package Crumb.Command;

import Crumb.TaskList;
import Crumb.Storage;

import java.io.IOException;

public abstract class Command {
    public abstract void execute(TaskList tasks, Storage storage) throws IOException;

    public boolean isExit() {
        return false;
    }
}
