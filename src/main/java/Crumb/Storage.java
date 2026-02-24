package Crumb;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

import Crumb.Task.Deadline;
import Crumb.Task.Event;
import Crumb.Task.Task;
import Crumb.Task.ToDo;


/**
 * Loads and saves tasks in the file
 */
public class Storage {

    /**
     * Delimiter between fields
     */
    protected static final String DELIMITER = "@@";

    /**
     * File path of txt file
     */
    protected String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads list of tasks from txt file
     * @return list of tasks in ArrayList
     */
    public ArrayList<Task> load() throws IOException {
        File file = new File(filePath);
        file.createNewFile();
        Scanner sc = new Scanner(file);
        ArrayList<Task> tasks = new ArrayList<>();
        int c = 0;

        while (sc.hasNextLine()) {
            String row = sc.nextLine();
            if (!isValidRow(row)) {
                continue;
            }
            String[] fields = row.split(DELIMITER);
            Task newTask;
            switch (row.charAt(1)) {
                case 'T' -> newTask = new ToDo(fields[1]);
                case 'D' -> newTask = new Deadline(fields[1], LocalDate.parse(fields[2]));
                case 'E' -> newTask = new Event(fields[1], LocalDate.parse(fields[2]), LocalDate.parse(fields[3]));
                default -> {

                    continue;
                }
            }
            c++;
            if (row.charAt(2) == '1') {
                newTask.markAsDone();
            }
            tasks.add(newTask);
        }
        sc.close();
        return tasks;
    }

    /**
     * Updates task file, called after create, update, delete operations.
     * @param tList TaskList object containing all current tasks
     */
    public void saveData(TaskList tList) throws IOException {
        File original = new File(filePath);
        String tmp = filePath.replace(".txt", ".tmp");
        File temp = new File(tmp);

        ArrayList<Task> tasks = tList.tasks;

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(temp))) {
            for (int i = 0; i < tasks.size(); i++) {
                String row = tasks.get(i).toFileString(i, DELIMITER);
                bw.write(row);
                bw.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Files.move(temp.toPath(), original.toPath(), StandardCopyOption.REPLACE_EXISTING);

    }

    /**
     * Checks if given task row is in valid format. Referenced in load()
     * Format: [index]T[isDone]@@[description]
     *         [index]D[isDone]@@[description]@@[by]
     *         [index]E[isDone]@@[description]@@[from]@@[to]
     * Date format: yyyy-MM-dd
     * @param row String representing a task
     * @return if row is in valid format.
     */
    public boolean isValidRow(String row) {
        if (row.length() < 6
                || !Character.isDigit(row.charAt(0))
                || "TED".indexOf(row.charAt(1)) == -1
                || !row.contains(DELIMITER)) {
            return false;
        }
        int fieldCount = row.split(DELIMITER).length;

        return (row.charAt(1) != 'D' || fieldCount >= 3)
                && (row.charAt(1) != 'E' || fieldCount >= 4);
    }


}
