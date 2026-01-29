import java.util.Scanner;

public class Crumb {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] list = new String[100];
        int idx = 0;
        String lineBreak = "-------------------------------------------------";
        System.out.println(lineBreak);
        System.out.println("Hey, I'm Crumb! ദ്ദി◝ ⩊ ◜)\nWhat can I do for you?");
        System.out.println("\033[3m[list, bye]\033[3m");
        System.out.println(lineBreak);

        String in = sc.nextLine();
        while (!in.equalsIgnoreCase("bye")) {
            if (in.equalsIgnoreCase("list")) {
                System.out.println(formatList(list, idx));
                in = sc.nextLine();
            } else {
                list[idx] = in;
                idx = (idx < 99) ? idx+1 : 0;
                System.out.println(">> Added " + in + "\n");
                in = sc.nextLine();
            }
        }
        System.out.println(formatList(list, idx));
        System.out.println("Bye! ヾ(￣▽￣)");
    }

    public static String formatList(String[] list, int n) {
        String out = "";
        for (int i=0; i < n; i++) {
            out += (i+1) + ". " + list[i] + "\n";
        }
        return out;
    }

}
