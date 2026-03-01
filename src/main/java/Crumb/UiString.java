package Crumb;


/**
 * Deals with interactions with the user
 */
public class UiString {

    /**
     * Displays welcome message, the first message on start
     */
    public static String getWelcome() {
        return "Hey, I'm Crumb! :)\nWhat can I do for you?\n" + getInstructionTable();
    }

    /**
     * Displays instruction table
     */
    public static String getInstructionTable() {
        return "    Adding tasks:\n"
                + "        - todo <desc>\n"
                + "        - deadline <desc> /by <DDMMYY>\n"
                + "        - event <desc> /from <DDMMYY> /to <DDMMYY>\n"
                + "    Updating tasks:\n"
                + "        - mark <num>\n"
                + "        - unmark <num>\n"
                + "        - delete <num>\n"
                + "    Showing tasks:\n"
                + "        - list\n"
                + "        - find\n"
                + "    Others:\n"
                + "        - help\n"
                + "        - bye";
    }

    /**
     * Displays formatted list of tasks
     * "list" command
     */
    public static String getList(String tasks) {
        return "Your Tasks: \n" + tasks;
    }


    /**
     * Displays success message for commands:
     * mark, unmark, add, remove
     */
    public static String getSuccessMessage(String command, String s) {
        String out = s;

        if (command.equals("mark")) {
            out += " completed. Good job!";
        } else if (command.equals("unmark")) {
            out += " marked as not done yet.";
        } else if (command.equals("delete")) {
            out += " removed from your list.";
        } else {
            out += " added to your list.";
        }
        return out;
    }

    /**
     * Displays results of "find" command
     */
    public static String getSearchResults(String results) {
        return "Here are the matching tasks in your list: \n" + results;
    }

    /**
     * Displays goodbye
     * "bye" command
     */
    public static String getGoodbye() {
        return "Bye!";
    }

    // ERRORS:

    /**
     * Missing description error
     * "todo", "deadline", "event" commands
     */
    public static String getMissingDescriptionError() {
        return "Your task is missing a description.";
    }

    /**
     * Missing deadline error
     * "deadline" command
     */
    public static String getMissingByError() {
        return "Your task is missing a deadline.\n"
                + "Format :     deadline description /by DDMMYY";
    }

    /**
     * Missing start date error
     * "event" command
     */
    public static String getMissingFromError() {
        return "Your event is missing a start date.\n"
                + "Format :     event <desc> /from <DDMMYY> /to <DDMMYY>";
    }

    /**
     * Missing end date error
     * "event" command
     */
    public static String getMissingToError() {
        return "Your event is missing an end date.\n"
                + "Format :     event <desc> /from <DDMMYY> /to <DDMMYY>";
    }

    /**
     * Missing keyword error
     * "find" command
     */
    public static String getMissingQueryError() {
        return "Your query is missing a keyword!\n"
                + "Format :     find <keyword>";
    }

    /**
     * Empty list
     */
    public static String getListEmptyError() {
        return "Your list is empty :0";
    }

    /**
     * Invalid index given (e.g. non-numerical)
     * "mark", "unmark", "delete" commands
     */
    public static String getInvalidIndexError() {
        return "That's not a valid index.";
    }

    /**
     * Date is in invalid format
     * "deadline", "event" commands
     */
    public static String getInvalidDateError() {
        return "Dates should be formatted DDMMYY (e.g. 1 March 2025 -> 010325)";
    }

    /**
     * Invalid command word
     */
    public static String getUnknownCommandError() {
        return "I'm not sure what that means :(\nHere's what I can do for you,\n" + getInstructionTable();
    }

    /**
     * Given index out of bounds
     */
    public static String getIndexOutOfBoundsError() {
        return "That task doesn't exist. You have " + Crumb.tasks.getCount() + " task(s).";
    }

    /**
     * Loading error
     * Referenced by Storage
     */
    public static String getLoadingError() {
        return "There was an issue loading your data.";
    }

    /**
     * Display generic error
     */
    public static String getErrorMessage(String error) {
        return "Oops, " + error;
    }

}
