import java.util.Scanner;

public class Crumb {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String lineBreak = "-------------------------------------------------";
        System.out.println(lineBreak);
        System.out.println("Hey, I'm Crumb! ദ്ദി◝ ⩊ ◜)\nWhat can I do for you?");
        System.out.println(lineBreak);

        String in = sc.nextLine();
        while (!in.toLowerCase().equals("bye")) {
            System.out.println(">> " + in + "\n");
            in = sc.nextLine();
        }
        System.out.println("Bye! ヾ(￣▽￣)");
    }

}
