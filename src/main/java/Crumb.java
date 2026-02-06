import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Crumb {

    protected static int count = 0;
    protected static String DELIMITER = "@@";
    public static void main(String[] args) throws IOException {

        Scanner sc = new Scanner(System.in);
        ArrayList<Task> tasks = loadData();

        //String lineBreak = "______________________________________________________________";
        String instTable = ".____________________________________________________________.\n" +
                "| Add Task                             | Update Task  |      |\n" +
                "|--------------------------------------|--------------| help |\n" +
                "| todo <desc>                          | mark <num>   | list |\n" +
                "| deadline <desc> /by <date>           | unmark <num> | bye  |\n" +
                "| event <desc> /from <date> /to <date> | delete <num> |      |\n" +
                "|______________________________________|______________|______|\n\n";

        System.out.println("Hey, I'm Crumb! ദ്ദി◝ ⩊ ◜)\nWhat can I do for you?");
        System.out.println(instTable);

        String in = sc.nextLine().trim();
        while (!in.equalsIgnoreCase("bye")) {

            String[] instruction = in.toLowerCase().split(" ", 2);

            try {

                switch (instruction[0]) {
                    case "help" -> System.out.println(instTable);
                    case "list" -> System.out.println(formatList(tasks, count));
                    case "mark" -> {
                        if (count == 0) {
                            throw new Exception("Your list is empty :0");
                        }
                        if (instruction.length < 2 || !instruction[1].matches("\\d+")) {
                            throw new NumberFormatException("Oops! That's not a valid index.");
                        }
                        int idx = Integer.parseInt(instruction[1]);
                        if (idx > count) {
                            throw new IndexOutOfBoundsException("Oops! That task doesn't exist. You have " + count + " task(s).");
                        }
                        Task target = tasks.get(idx-1); //idx given is 1-based
                        target.markAsDone();
                        System.out.println("Task " + idx + ", " + target.description + " completed. Good job!\n");

                        saveData(tasks);
                    }
                    case "unmark" -> {
                        if (count == 0) {
                            throw new Exception("Your list is empty :0");
                        }
                        if (instruction.length < 2 || !instruction[1].matches("\\d+")) {
                            throw new NumberFormatException("Oops! That's not a valid index.");
                        }
                        int idx = Integer.parseInt(instruction[1]);
                        if (idx > count) {
                            throw new IndexOutOfBoundsException("Oops! That task doesn't exist. You have " + count + " task(s).");
                        }
                        Task target = tasks.get(idx-1); //idx given is 1-based
                        target.unmark();
                        System.out.println("Task " + idx + ", " + target.description + " marked as not done yet.\n");

                        saveData(tasks);
                    }
                    case "delete" -> {
                        if (count == 0) {
                            throw new Exception("Your list is empty :0");
                        }
                        if (instruction.length < 2 || !instruction[1].matches("\\d+")) {
                            throw new NumberFormatException("Oops! That's not a valid index.");
                        }
                        int idx = Integer.parseInt(instruction[1]);
                        if (idx > count) {
                            throw new IndexOutOfBoundsException("Oops! That task doesn't exist. You have " + count + " task(s).");
                        }
                        Task target = tasks.get(idx-1); //idx given is 1-based
                        tasks.remove(target);
                        System.out.println("I've removed the task\n" + target.toString() + "\nfrom your list.\n");
                        count--;

                        saveData(tasks);
                    }
                    case "todo" -> {
                        if (instruction.length < 2) {
                            throw new IndexOutOfBoundsException("Oops! Your task is missing a description.");
                        }
                        String body = instruction[1];
                        Task newTask = new ToDo(body);
                        tasks.add(newTask);
                        count++;
                        System.out.println(">> Added task: " + body + "\n");

                        saveData(tasks);
                    }
                    case "deadline" -> {
                        if (instruction.length < 2) {
                            throw new IndexOutOfBoundsException("Oops! Your task is missing a description.");
                        }
                        String body = instruction[1];
                        String[] s = body.split(" /by ", 2);
                        if (s.length < 2) {
                            throw new IndexOutOfBoundsException("Oops! Your task is missing a deadline -> /by <date>");
                        }
                        String desc = s[0];
                        String by = s[1];
                        Task newTask = new Deadline(desc, by);
                        tasks.add(newTask);
                        count++;
                        System.out.println(">> Added task: " + desc + " due by " + by + "\n");

                        saveData(tasks);
                    }
                    case "event" -> {
                        if (instruction.length < 2) {
                            throw new IndexOutOfBoundsException("Oops! Your task is javamissing a description.");
                        }
                        String body = instruction[1];
                        String[] s1 = body.split(" /from ", 2);
                        if (s1.length < 2) {
                            throw new IndexOutOfBoundsException("Oops! Your event is missing a start date/time -> /from <date>");
                        }
                        String desc = s1[0];
                        String[] s2 = s1[1].split(" /to ", 2);
                        if (s2.length < 2) {
                            throw new IndexOutOfBoundsException("Oops! Your event is missing a end date/time -> /to <date>");
                        }
                        String from = s2[0];
                        String to = s2[1];
                        Task newTask = new Event(desc, from, to);
                        tasks.add(newTask);
                        count++;
                        System.out.println(">> Added event " + desc + ", from " + from + " to " + to + "\n");

                        saveData(tasks);
                    }
                    default -> {
                        System.out.println("I'm not sure what that means :(\nHere's what I can do for you,");
                        System.out.println(instTable);
                    }
                }
            } catch (Exception e) {
                String error = e.getMessage();
                if (Objects.equals(error, "")) {
                    System.out.println(e + "\n");
                } else {
                    System.out.println(error + "\n");
                }
                in = sc.nextLine();
                continue;
            }
            in = sc.nextLine();;
        }
        System.out.println("Bye! ヾ(￣▽￣)");
    }

    /**
     * Returns tasks as formatted multi-line string
     *
     * @param tasks the list of tasks to be formatted.
     * @param n count; number of tasks in the list.
     * @return Formatted task list as String.
     */
    public static String formatList(ArrayList<Task> tasks, int n) {
        String out = "";
        for (int i=0; i < n; i++) {
            out += (i+1) + ". " + tasks.get(i).toString() + "\n";
        }
        return out;
    }

    public static ArrayList<Task> loadData() throws IOException {
        File file = new File("data/crumb.txt");
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
                case 'D' -> newTask = new Deadline(fields[1], fields[2]);
                case 'E' -> newTask = new Event(fields[1], fields[2], fields[3]);
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
        count = c;
        sc.close();
        return tasks;
    }

    public static boolean isValidRow(String row) {
        if (row.length() < 6
                || !Character.isDigit(row.charAt(0))
                || "TED".indexOf(row.charAt(1)) == -1
                || !row.contains("@@")) {
            return false;
        }
        int fieldCount = row.split(DELIMITER).length;

        return (row.charAt(1) != 'D' || fieldCount >= 3)
                && (row.charAt(1) != 'E' || fieldCount >= 4);
    }

    public static void saveData(ArrayList<Task> tasks) throws IOException {
        File original = new File("data/crumb.txt");
        File temp = new File("data/crumb.tmp");

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(temp))) {
            for (int i=0; i < tasks.size(); i++) {
                String row = tasks.get(i).toFileString(i);
                bw.write(row);
                bw.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Files.move(temp.toPath(), original.toPath(), StandardCopyOption.REPLACE_EXISTING);

    }

}
