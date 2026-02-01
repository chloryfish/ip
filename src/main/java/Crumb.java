import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Crumb {

    protected static int count = 0;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> list = new ArrayList<>();

        //String lineBreak = "______________________________________________________________";
        String instTable = ".____________________________________________________________.\n" +
                "| Add Task                             | Update Task  |      |\n" +
                "|--------------------------------------|--------------| help |\n" +
                "| todo <desc>                          | mark <num>   | list |\n" +
                "| deadline <desc> /by <date>           | unmark <num> | bye  |\n" +
                "| event <desc> /from <date> /to <date> |              |      |\n" +
                "|______________________________________|______________|______|\n\n";

        System.out.println("Hey, I'm Crumb! ദ്ദി◝ ⩊ ◜)\nWhat can I do for you?");
        System.out.println(instTable);

        String in = sc.nextLine().trim();
        while (!in.equalsIgnoreCase("bye")) {

            String[] instruction = in.toLowerCase().split(" ", 2);

            try {

                switch (instruction[0]) {
                    case "help" -> System.out.println(instTable);
                    case "list" -> System.out.println(formatList(list, count));
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
                        Task target = list.get(idx-1); //idx given is 1-based
                        target.markAsDone();
                        System.out.println("Task " + idx + ", " + target.description + " completed. Good job!\n");


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
                        Task target = list.get(idx-1); //idx given is 1-based
                        target.unmark();
                        System.out.println("Task " + idx + ", " + target.description + " marked as not done yet.\n");
                    }
                    case "todo" -> {
                        if (instruction.length < 2) {
                            throw new IndexOutOfBoundsException("Oops! Your task is missing a description.");
                        }
                        String body = instruction[1];
                        Task newTask = new ToDo(body);
                        list.add(newTask);
                        count++;
                        System.out.println(">> Added task: " + body + "\n");
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
                        list.add(newTask);
                        count++;
                        System.out.println(">> Added task: " + desc + " due by " + by + "\n");
                    }
                    case "event" -> {
                        if (instruction.length < 2) {
                            throw new IndexOutOfBoundsException("Oops! Your task is missing a description.");
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
                        list.add(newTask);
                        count++;
                        System.out.println(">> Added event " + desc + ", from " + from + " to " + to + "\n");
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
     * @param list the list of tasks to be formatted.
     * @param n count; number of tasks in the list.
     * @return Formatted task list as String.
     */
    public static String formatList(ArrayList<Task> list, int n) {
        String out = "";
        for (int i=0; i < n; i++) {
            out += (i+1) + ". " + list.get(i).toString() + "\n";
        }
        return out;
    }

}
