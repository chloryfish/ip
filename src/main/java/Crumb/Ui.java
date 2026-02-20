package Crumb;

import java.util.Scanner;

public class Ui {

    public static String readCommand() {
        Scanner sc = new Scanner(System.in);
        return sc.nextLine().trim();
    }

    public static void showWelcome() {
        System.out.println("Hey, I'm Crumb! ദ്ദി◝ ⩊ ◜)\nWhat can I do for you?");
        showInstructionTable();
    }

    public static void showInstructionTable() {
        String INSTRUCTIONS = ".________________________________________________________________.\n" +
                "| Add Task                                 | Update Task  |      |\n" +
                "|------------------------------------------|--------------| help |\n" +
                "| todo <desc>                              | mark <num>   | list |\n" +
                "| deadline <desc> /by <DDMMYY>             | unmark <num> | bye  |\n" +
                "| event <desc> /from <DDMMYY> /to <DDMMYY> | delete <num> |      |\n" +
                "|__________________________________________|______________|______|\n";
        System.out.println(INSTRUCTIONS);
    }

    public static void displayList(String tasks) {
        System.out.println("Your Tasks: ");
        System.out.println(tasks);
    }


    public static void showSuccessMessage(String command, String s) {
        String out = ">> ";
        out+= s;

        if (command.equals("mark")) {
            out+= " completed. Good job!\n";
        } else if (command.equals("unmark")) {
            out+= " marked as not done yet.\n";
        } else if (command.equals("delete")) {
            out+= " removed from your list.\n";
        } else {
            out+= " added to your list.\n";
        }
        System.out.println(out);
    }

    public static void showGoodbye() {
        System.out.println("Bye! ヾ(￣▽￣)");
    }

    public static void newLine() {
        //System.out.println("\n");
    }



    // DISPLAYING ERRORS:

    public static void showMissingDescriptionError() {
        System.out.println("Oops! Your task is missing a description.\n");
    }

    public static void showMissingByError() {
        System.out.println("Oops! Your task is missing a deadline.\n" +
                "Format :     deadline description /by DDMMYY\n");
    }

    public static void showMissingFromError() {
        System.out.println("Oops! Your event is missing a start date.\n" +
                "Format :     event <desc> /from <DDMMYY> /to <DDMMYY>\n");
    }

    public static void showMissingToError() {
        System.out.println("Oops! Your event is missing an end date.\n" +
                "Format :     event <desc> /from <DDMMYY> /to <DDMMYY>\n");
    }

    public static void showListEmptyError() {
        System.out.println("Your list is empty :0\n");
    }

    public static void showInvalidIndexError() {
        System.out.println("Oops! That's not a valid index.\n");
    }

    public static void showInvalidDateError() {
        System.out.println("Oops! Dates should be formatted DDMMYY (e.g. 1 March 2025 -> 010325)\n");
    }

    public static void showUnknownCommandError() {
        System.out.println("I'm not sure what that means :(\nHere's what I can do for you,");
        showInstructionTable();
    }

    public static void showIndexOutOfBoundsError() {
        System.out.println("Oops! That task doesn't exist. You have " + Crumb.tasks.getCount() + " task(s).\n");
    }

    public static void showLoadingError() {
        System.out.println("There was an issue loading your data.\n");
    }

    public static void showError(String error) {
        System.out.println("Uh oh, " + error);
    }

}
