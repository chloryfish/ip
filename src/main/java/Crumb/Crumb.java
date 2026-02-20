package Crumb;

import Crumb.Command.Command;
import Crumb.Task.*;
import java.util.ArrayList;

public class Crumb {

    private final Storage storage;
    public static TaskList tasks;

    public Crumb(String filePath) {
        storage = new Storage(filePath);
        try {
            ArrayList<Task> tList = storage.load();
            tasks = new TaskList(tList);
        } catch (Exception e) {
            Ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void run() {
        Ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = Ui.readCommand();
                Command c = Parser.parse(fullCommand);
                if (c != null) {
                    c.execute(tasks, storage);
                    isExit = c.isExit();
                }
            } catch (Exception e) {
                Ui.showError(e.getMessage());
            } finally {
                Ui.newLine();
            }
        }
    }
    public static void main(String[] args){
        new Crumb("data/tasks.txt").run();
    }

}
