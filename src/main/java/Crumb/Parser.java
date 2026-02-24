package Crumb;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import Crumb.Command.Command;
import Crumb.Command.CreateDeadlineCommand;
import Crumb.Command.CreateEventCommand;
import Crumb.Command.CreateToDoCommand;
import Crumb.Command.DeleteCommand;
import Crumb.Command.ExitCommand;
import Crumb.Command.FindCommand;
import Crumb.Command.HelpCommand;
import Crumb.Command.ListCommand;
import Crumb.Command.MarkCommand;
import Crumb.Command.UnmarkCommand;



/**
 * Deals with making sense of the user command
 */
public class Parser {

    /**
     * Datetime formatter for inputs
     */
    protected static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("ddMMyy");

    /**
     * Parses user command
     * @param in User command as a string
     * @return corresponding Command object
     */
    public static Command parse(String in) {
        String[] instruction = in.split(" ", 2);

        try {
            switch (instruction[0].toLowerCase()) {
                case "todo" -> {
                    if (instruction.length < 2) {
                        Ui.showMissingDescriptionError();
                        return null;
                    }
                    return new CreateToDoCommand(instruction[1]);
                }
                case "deadline" -> {
                    if (instruction.length < 2) {
                        Ui.showMissingDescriptionError();
                        return null;
                    }
                    String body = instruction[1];
                    String[] s = body.split(" /by ", 2);
                    if (s.length < 2) {
                        Ui.showMissingByError();
                        return null;
                    }

                    LocalDate by = parseDate(s[1]);
                    if (by == null) {
                        return null;
                    }
                    return new CreateDeadlineCommand(s[0], by);
                }
                case "event" -> {
                    if (instruction.length < 2) {
                        Ui.showMissingDescriptionError();
                        return null;
                    }
                    String body = instruction[1];
                    String[] s1 = body.split(" /from ", 2);
                    if (s1.length < 2) {
                        Ui.showMissingFromError();
                        return null;
                    }
                    String desc = s1[0];
                    String[] s2 = s1[1].split(" /to ", 2);
                    if (s2.length < 2) {
                        Ui.showMissingToError();
                        return null;
                    }
                    LocalDate from = parseDate(s2[0]);
                    LocalDate to = parseDate(s2[1]);
                    if (from == null || to == null) {
                        return null;
                    }
                    return new CreateEventCommand(desc, from, to);

                }
                case "mark" -> {
                    if (instruction.length < 2 || !instruction[1].matches("\\d+")) {
                        Ui.showInvalidIndexError();
                        return null;
                    }
                    int idx = Integer.parseInt(instruction[1]);
                    return new MarkCommand(idx - 1);
                }
                case "unmark" -> {
                    if (instruction.length < 2 || !instruction[1].matches("\\d+")) {
                        Ui.showInvalidIndexError();
                        return null;
                    }
                    int idx = Integer.parseInt(instruction[1]);
                    return new UnmarkCommand(idx - 1);

                }
                case "delete" -> {
                    if (instruction.length < 2 || !instruction[1].matches("\\d+")) {
                        Ui.showInvalidIndexError();
                        return null;
                    }
                    int idx = Integer.parseInt(instruction[1]);
                    return new DeleteCommand(idx - 1);
                }
                case "find" -> {
                    if (instruction.length < 2) {
                        Ui.showMissingQueryError();
                        return null;
                    }
                    return new FindCommand(instruction[1]);
                }
                case "list" -> {
                    return new ListCommand();
                }
                case "help" -> {
                    return new HelpCommand();
                }
                case "bye" -> {
                    return new ExitCommand();
                }
                default -> {
                    Ui.showUnknownCommandError();
                    return null;
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Converts user-given string to LocalDate object
     * @param dateString user-given date string
     * @return LocalDate object
     */
    public static LocalDate parseDate(String dateString) {
        try {
            return LocalDate.parse(dateString, FORMATTER);
        } catch (DateTimeParseException e) {
            Ui.showInvalidDateError();
            return null;
        }
    }

    /**
     * Converts LocalDate object to readable string
     * @param date LocalDate object
     * @return readable string with format d MMM yyyy (e.g. "1 Jan 2026")
     */
    public static String formatDateReadable(LocalDate date) {
        return date.format(DateTimeFormatter.ofPattern("d MMM yyyy"));
    }


}
