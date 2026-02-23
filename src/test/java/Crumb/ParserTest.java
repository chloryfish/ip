package Crumb;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Objects;

import org.junit.jupiter.api.Test;

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

public class ParserTest {

    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    @Test
    public void parse_standardCommands_success() {
        assertEquals(
                CreateToDoCommand.class,
                Objects.requireNonNull(Parser.parse("todo clean desk")).getClass());

        assertEquals(
                CreateDeadlineCommand.class,
                Objects.requireNonNull(Parser.parse("deadline buy power strip /by 101020")).getClass());

        assertEquals(
                CreateEventCommand.class,
                Objects.requireNonNull(Parser.parse("event work trip /from 101020 /to 201020")).getClass());

        assertEquals(
                MarkCommand.class,
                Objects.requireNonNull(Parser.parse("mark 1")).getClass());

        assertEquals(
                UnmarkCommand.class,
                Objects.requireNonNull(Parser.parse("unmark 1")).getClass());

        assertEquals(
                DeleteCommand.class,
                Objects.requireNonNull(Parser.parse("delete 1")).getClass());

        assertEquals(
                FindCommand.class,
                Objects.requireNonNull(Parser.parse("find trip")).getClass());

        assertEquals(
                ListCommand.class,
                Objects.requireNonNull(Parser.parse("list")).getClass());

        assertEquals(
                HelpCommand.class,
                Objects.requireNonNull(Parser.parse("help")).getClass());

        assertEquals(
                ExitCommand.class,
                Objects.requireNonNull(Parser.parse("bye")).getClass());
    }

    @Test
    public void parse_caseInsensitive_success() {
        assertEquals(
                CreateToDoCommand.class,
                Objects.requireNonNull(Parser.parse("TODO clean desk")).getClass());

        assertEquals(
                CreateDeadlineCommand.class,
                Objects.requireNonNull(Parser.parse("DeadLine buy power strip /by 101020")).getClass());
    }

    @Test
    public void parse_missingDescription_errorMessage() {
        System.setOut(new PrintStream(outputStream));
        // Expected string
        String missingDescription = "Oops! Your task is missing a description.\n" + System.lineSeparator();
        // Test
        Parser.parse("todo");
        assertEquals(
                missingDescription,
                outputStream.toString());
        // Restore system out
        System.setOut(originalOut);
    }

    @Test
    public void parse_invalidDate_errorMessage() {
        System.setOut(new PrintStream(outputStream));
        // Expected string
        String invalidDate = "Oops! Dates should be formatted DDMMYY (e.g. 1 March 2025 -> 010325)\n"
                + System.lineSeparator();
        // Test
        Parser.parse("deadline buy power strip /by 10202000");
        assertEquals(
                invalidDate,
                outputStream.toString());
        // Restore system out
        System.setOut(originalOut);
    }
}
