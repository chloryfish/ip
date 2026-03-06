package Crumb;

import java.util.ArrayList;

import Crumb.Command.Command;
import Crumb.Task.Task;

/**
 * Main class for Application
 */
public class Crumb {

    public static TaskList tasks;
    private final Storage storage;

    /**
     * Constructor for Crumb class
     * @param filePath filepath for local task file
     */
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

    /**
     * Returns a response object, given user input as string
     * @param userInput user-provided command
     * @return response
     */
    public Response getResponse(String userInput) {
        try {
            Command c = Parser.parse(userInput);
            if (c != null) {
                Response output = c.execute(tasks, storage);
                if (c.isExit()) {
                    System.exit(0);
                }
                return output;
            }
        } catch (Exception e) {
            return new Response(UiString.getErrorMessage(e.getMessage()));
        }
        return new Response("");
    }

}
