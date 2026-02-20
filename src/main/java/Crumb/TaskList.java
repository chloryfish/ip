package Crumb;

import Crumb.Task.Deadline;
import Crumb.Task.Event;
import Crumb.Task.Task;
import Crumb.Task.ToDo;

import java.time.LocalDate;
import java.util.ArrayList;

public class TaskList {

    protected ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public int getCount() {
        return this.tasks.size();
    }

    public Task getTask(int index) {
        return this.tasks.get(index);
    }

    public void deleteTask(int index) {
        this.tasks.remove(getTask(index));
    }

    public int addTask(String description) {
        Task t = new ToDo(description);
        this.tasks.add(t);
        return getCount()-1;
    }

    public int addTask(String description, LocalDate by) {
        Task t = new Deadline(description, by);
        this.tasks.add(t);
        return getCount()-1;
    }

    public int addTask(String description, LocalDate from, LocalDate to) {
        Task t = new Event(description, from, to);
        this.tasks.add(t);
        return getCount()-1;
    }

    public void markTask(int index) {
        this.tasks.get(index).markAsDone();
    }

    public void unMarkTask(int index) {
        this.tasks.get(index).unmark();
    }

    public ArrayList<Task> findTasks(String keyword) {
        ArrayList<Task> out = new ArrayList<>();
        for (Task t : this.tasks) {
            if (t.getDescription().contains(keyword)) {
                out.add(t);
            }
        }
        return out;
    }

    public String formatList() {
        String out = "";
        for (int i=0; i < this.getCount(); i++) {
            out += (i+1) + ". " + tasks.get(i).toString() + "\n";
        }
        return out;
    }

    public String formatList(ArrayList<Task> t) {
        String out = "";
        for (int i=0; i < t.size(); i++) {
            out += (i+1) + ". " + t.get(i).toString() + "\n";
        }
        return out;
    }

    public String getShorthand(int index) {
        return this.tasks.get(index).toShorthand(index+1);
    }

}
