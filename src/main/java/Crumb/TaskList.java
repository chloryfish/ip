package Crumb;

import java.time.LocalDate;
import java.util.ArrayList;

import Crumb.Task.Deadline;
import Crumb.Task.Event;
import Crumb.Task.Task;
import Crumb.Task.ToDo;


/**
 * Contains the task list.
 * Has operations to add / delete tasks in the list
 */
public class TaskList {

    /**
     * List of Task objects
     */
    protected ArrayList<Task> tasks;

    /**
     * Constructor - empty task list
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructor, referenced by Storage:load()
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * @return size of task list
     */
    public int getCount() {
        return this.tasks.size();
    }

    /**
     * Gets a task
     * @param index of task
     * @return Task object
     */
    public Task getTask(int index) {
        return this.tasks.get(index);
    }

    /**
     * Deletes a task
     * @param index of task to be deleted
     */
    public void deleteTask(int index) {
        this.tasks.remove(getTask(index));
    }

    /**
     * Adds a TD task to the task list
     * @param description of new task
     * @return index (0-based) of new task
     */
    public int addTask(String description) {
        Task t = new ToDo(description);
        this.tasks.add(t);
        return getCount() - 1;
    }

    /**
     * Adds a Deadline task to the task list
     * @param description of new task
     * @param by deadline of task as LocalDate
     * @return index (0-based) of new task
     */
    public int addTask(String description, LocalDate by) {
        Task t = new Deadline(description, by);
        this.tasks.add(t);
        return getCount() - 1;
    }

    /**
     * Adds a Event task to the task list
     * @param description of new task
     * @param from LocalDate, start of event
     * @param to LocalDate, start of event
     * @return index (0-based) of new task
     */
    public int addTask(String description, LocalDate from, LocalDate to) {
        Task t = new Event(description, from, to);
        this.tasks.add(t);
        return getCount() - 1;
    }

    /**
     * Marks a task as done
     * @param index of task
     */
    public void markTask(int index) {
        this.tasks.get(index).markAsDone();
    }

    /**
     * Marks a task as not done
     * @param index of task
     */
    public void unMarkTask(int index) {
        this.tasks.get(index).unmark();
    }

    /**
     * Finds tasks that contain a certain keyword in their description
     * @param keyword to find in descriptions
     * @return ArrayList of tasks that match query
     */
    public ArrayList<Task> findTasks(String keyword) {
        ArrayList<Task> out = new ArrayList<>();
        for (Task t : this.tasks) {
            if (t.getDescription().contains(keyword)) {
                out.add(t);
            }
        }
        return out;
    }

    /**
     * Returns this task list as formatted multi-line string
     * @return formatted string
     */
    public String formatList() {
        String out = "";
        for (int i = 0; i < this.getCount(); i++) {
            out += (i + 1) + ". " + tasks.get(i).toString() + "\n";
        }
        return out;
    }

    /**
     * Returns given task list as formatted multi-line string
     * Referenced by FindCommand
     * @return formatted string
     */
    public static String formatList(ArrayList<Task> t) {
        String out = "";
        for (int i = 0; i < t.size(); i++) {
            out += (i + 1) + ". " + t.get(i).toString() + "\n";
        }
        return out;
    }

    /**
     * Returns short readable string for a single task
     * @param index of task
     * @return formatted string
     */
    public String getShorthand(int index) {
        return this.tasks.get(index).toShorthand(index + 1);
    }

}
