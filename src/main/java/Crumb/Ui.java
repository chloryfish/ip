package Crumb;

import java.util.Scanner;


/**
 * Deals with interactions with the user
 */
public class Ui {

    /**
     * Reads user input
     * @return trimmed user input string
     */
    public static String readCommand() {
        Scanner sc = new Scanner(System.in);
        return sc.nextLine().trim();
    }

    /**
     * Displays welcome message, the first message on start
     */
    public static void showWelcome() {
        System.out.println("Hey, I'm Crumb! :)\nWhat can I do for you?");
        showInstructionTable();
    }

    /**
     * Displays instruction table
     */
    public static void showInstructionTable() {
        String instructions = ".________________________________________________________________.\n"
                + "| Add Task                                 | Update Task  |      |\n"
                + "|------------------------------------------|--------------| list |\n"
                + "| todo <desc>                              | mark <num>   | find |\n"
                + "| deadline <desc> /by <DDMMYY>             | unmark <num> | help |\n"
                + "| event <desc> /from <DDMMYY> /to <DDMMYY> | delete <num> | bye  |\n"
                + "|__________________________________________|______________|______|\n";
        System.out.println(instructions);
    }

    /**
     * Displays formatted list of tasks
     * "list" command
     */
    public static void displayList(String tasks) {
        System.out.println("Your Tasks: ");
        System.out.println(tasks);
    }


    /**
     * Displays success message for commands:
     * mark, unmark, add, remove
     */
    public static void showSuccessMessage(String command, String s) {
        String out = ">> ";
        out += s;

        if (command.equals("mark")) {
            out += " completed. Good job!\n";
        } else if (command.equals("unmark")) {
            out += " marked as not done yet.\n";
        } else if (command.equals("delete")) {
            out += " removed from your list.\n";
        } else {
            out += " added to your list.\n";
        }
        System.out.println(out);
    }

    /**
     * Displays results of "find" command
     */
    public static void showSearchResults(String results) {
        System.out.println("Here are the matching tasks in your list: ");
        System.out.println(results);
    }

    /**
     * Displays goodbye
     * "bye" command
     */
    public static void showGoodbye() {
        System.out.println("Bye!");
    }

    /**
     * Displays newline
     */
    public static void newLine() {
        //System.out.println("\n");
    }

    // ERRORS:

    /**
     * Missing description error
     * "todo", "deadline", "event" commands
     */
    public static void showMissingDescriptionError() {
        System.out.println("Oops! Your task is missing a description.\n");
    }

    /**
     * Missing deadline error
     * "deadline" command
     */
    public static void showMissingByError() {
        System.out.println("Oops! Your task is missing a deadline.\n"
                + "Format :     deadline description /by DDMMYY\n");
    }

    /**
     * Missing start date error
     * "event" command
     */
    public static void showMissingFromError() {
        System.out.println("Oops! Your event is missing a start date.\n"
                + "Format :     event <desc> /from <DDMMYY> /to <DDMMYY>\n");
    }

    /**
     * Missing end date error
     * "event" command
     */
    public static void showMissingToError() {
        System.out.println("Oops! Your event is missing an end date.\n"
                + "Format :     event <desc> /from <DDMMYY> /to <DDMMYY>\n");
    }

    /**
     * Missing keyword error
     * "find" command
     */
    public static void showMissingQueryError() {
        System.out.println("Oops! Your query is missing a keyword!\n"
                + "Format :     find <keyword>\n");
    }

    /**
     * Empty list
     */
    public static void showListEmptyError() {
        System.out.println("Your list is empty :0\n");
    }

    /**
     * Invalid index given (e.g. non-numerical)
     * "mark", "unmark", "delete" commands
     */
    public static void showInvalidIndexError() {
        System.out.println("Oops! That's not a valid index.\n");
    }

    /**
     * Date is in invalid format
     * "deadline", "event" commands
     */
    public static void showInvalidDateError() {
        System.out.println("Oops! Dates should be formatted DDMMYY (e.g. 1 March 2025 -> 010325)\n");
    }

    /**
     * Invalid command word
     */
    public static void showUnknownCommandError() {
        System.out.println("I'm not sure what that means :(\nHere's what I can do for you,");
        showInstructionTable();
    }

    /**
     * Given index out of bounds
     */
    public static void showIndexOutOfBoundsError() {
        System.out.println("Oops! That task doesn't exist. You have " + Crumb.tasks.getCount() + " task(s).\n");
    }

    /**
     * Loading error
     * Referenced by Storage
     */
    public static void showLoadingError() {
        System.out.println("There was an issue loading your data.\n");
    }

    /**
     * Display generic error
     */
    public static void showError(String error) {
        System.out.println("Uh oh, " + error);
    }

}
