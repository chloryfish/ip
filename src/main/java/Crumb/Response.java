package Crumb;

import java.util.ArrayList;

import Crumb.Task.Task;
import javafx.util.Pair;

public class Response {

    private String message;
    private boolean isHelp = false;

    private ArrayList<Pair<Integer, Task>> taskList = new ArrayList<>();

    public Response(String message) {
        this.message = message;
    }

    public Response(String message, boolean isHelp) {
        this.message = message;
        this.isHelp = isHelp;
    }

    public Response(String message, ArrayList<Pair<Integer, Task>> taskList) {
        this.message = message;
        this.taskList = taskList;
    }

    public String getMessage() {
        return message;
    }

    public ArrayList<Pair<Integer, Task>> getTaskList() {
        return taskList;
    }

    public boolean hasTasks() {
        return !this.taskList.isEmpty();
    }

    public boolean isHelp() {
        return this.isHelp;
    }
}