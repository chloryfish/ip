import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Crumb {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> list = new ArrayList<>();
        int count = 0;

        String lineBreak = "-------------------------------------------------";
        System.out.println(lineBreak);
        System.out.println("Hey, I'm Crumb! ദ്ദി◝ ⩊ ◜)\nWhat can I do for you?");
        System.out.println("\033[3m[list, mark <num>, unmark <num>, bye]\033[3m");
        System.out.println(lineBreak);

        String in = sc.nextLine();
        while (!in.equalsIgnoreCase("bye")) {
            String command = in.toLowerCase().split(" ")[0];

            if (command.equals("list")) {
                System.out.println(formatList(list, count));

            } else if (command.equals("mark")) {
                if (count == 0) {
                    System.out.println("Your list is empty :0\n");
                } else {
                    try {
                        int targetIdx = Integer.parseInt(in.toLowerCase().split(" ")[1]);
                        Task target = list.get(targetIdx-1);
                        target.markAsDone();
                        System.out.println("Task " + targetIdx + ", " + target.description + " completed. Good job!\n");
                    } catch (Exception e) { //NumberFormatException or IndexOutOfBoundsException
                        System.out.println("Oops! That's not a valid task number.\n");
                        in = sc.nextLine();
                        continue;
                    }
                }
            } else if (command.equals("unmark")) {
                if (count == 0) {
                    System.out.println("Your list is empty :0\n");
                } else {
                    try {
                        int targetIdx = Integer.parseInt(in.toLowerCase().split(" ")[1]);
                        Task target = list.get(targetIdx-1);
                        target.markAsUndone();
                        System.out.println("Task " + targetIdx + ", " + target.description + " marked as not done yet.\n");
                    } catch (Exception e) { //NumberFormatException or IndexOutOfBoundsException
                        System.out.println("Oops! That's not a valid task number.\n");
                        in = sc.nextLine();
                        continue;
                    }
                }
            } else {
                Task newTask = new Task(in);
                list.add(newTask);
                count++;
                System.out.println(">> Added " + in + "\n");
            }
            in = sc.nextLine();;
        }
        System.out.println("Bye! ヾ(￣▽￣)");
    }

    public static String formatList(ArrayList<Task> list, int n) {
        String out = "";
        for (int i=0; i < n; i++) {
            out += (i+1) + ". [" + list.get(i).getStatusIcon() + "] " + list.get(i).description + "\n";
        }
        return out;
    }

}
