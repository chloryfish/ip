package Crumb;

import Crumb.Command.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Parser {

    protected static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyy");

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
                    /*if (idx > count) {
                        throw new IndexOutOfBoundsException("Oops! That task doesn't exist. You have " + count + " task(s).");
                    }*/
                    return new MarkCommand(idx-1);
                }
                case "unmark" -> {
                    if (instruction.length < 2 || !instruction[1].matches("\\d+")) {
                        Ui.showInvalidIndexError();
                        return null;
                    }
                    int idx = Integer.parseInt(instruction[1]);
                    /*if (idx > count) {
                        throw new IndexOutOfBoundsException("Oops! That task doesn't exist. You have " + count + " task(s).");
                    }*/
                    return new UnmarkCommand(idx-1);

                }
                case "delete" -> {
                    /*int count = Crumb.count;
                    if (count == 0) {
                        throw new Exception("Your list is empty :0");
                    }*/
                    if (instruction.length < 2 || !instruction[1].matches("\\d+")) {
                        Ui.showInvalidIndexError();
                        return null;
                    }
                    int idx = Integer.parseInt(instruction[1]);
                    /*if (idx > count) {
                        throw new IndexOutOfBoundsException("Oops! That task doesn't exist. You have " + count + " task(s).");
                    }*/
                    return new DeleteCommand(idx-1);
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

    public static LocalDate parseDate(String dateString) {
        try {
            return LocalDate.parse(dateString, formatter);
        } catch (DateTimeParseException e) {
            Ui.showInvalidDateError();
            return null;
        }
    }

    public static String formatDateReadable(LocalDate date) {
        return date.format(DateTimeFormatter.ofPattern("d MMM yyyy"));
    }


}
