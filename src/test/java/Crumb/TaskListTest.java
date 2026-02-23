package Crumb;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TaskListTest {

    @Test
    public void findTasks_standardQuery_success() {
        TaskList tasks = new TaskList();
        tasks.addTask("todo clean desk");
        tasks.addTask("buy powerstrip",
                Parser.parseDate("101020"));
        tasks.addTask("event work trip",
                Parser.parseDate("101020"),
                Parser.parseDate("201020"));
        // Test
        String expected = "[[D][ ] buy powerstrip (by: 10 Oct 2020), "
                + "[E][ ] event work trip (from: 10 Oct 2020 to: 20 Oct 2020)]";
        assertEquals(expected,
                tasks.findTasks("trip").toString());
    }
}
