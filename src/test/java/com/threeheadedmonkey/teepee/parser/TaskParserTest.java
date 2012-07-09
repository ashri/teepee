package com.threeheadedmonkey.teepee.parser;

import com.threeheadedmonkey.teepee.entity.Task;
import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;

/**
 * Unit test for the TaskParser
 */
public class TaskParserTest {

    private TaskParser parser;
    private String[] lines;

    @Before
    public void setUp() throws Exception {
        this.parser = new TaskParser();

        lines = new String[6];
        lines[0] = "\t- Complete Performance Engineering Report";
        lines[1] = "\t- Complete timesheet for CSC & TOAU @today";
        lines[2] = "\t- Invoice Systemic for the previous month @due(2012-06-29)";
        lines[3] = "\t- Check out JDBI @url(http://www.jdbi.org/archive.html)";
        lines[4] = "  - Mow lawn @overdue";
        lines[5] = "- Install blinds throughout the house @tomorrow @rennovation";
    }

    @Test
    public void testGetPattern() throws Exception {

        for (String line : lines) {
            boolean matches = parser.getPattern().matcher(line).matches();
            assertTrue(line, matches);
        }
    }

    @Test
    public void testParseLine() throws Exception {
        Task task0 = parser.parseLine(lines[0]);
        assertNotNull(task0);
        assertEquals("Complete Performance Engineering Report", task0.getContent());
        assertFalse(task0.isOverdue());
        assertNull(task0.getDueDate());
        assertFalse(task0.hasTags());

        Task task1 = parser.parseLine(lines[1]);
        assertNotNull(task1);
        assertEquals("Complete timesheet for CSC & TOAU", task1.getContent());
        assertFalse(task1.isOverdue());
        assertEquals(new DateTime().toString("dd/MM/yyyy"), new DateTime(task1.getDueDate()).toString("dd/MM/yyyy"));
        assertFalse(task1.hasTags());

        Task task2 = parser.parseLine(lines[2]);
        assertNotNull(task2);
        assertEquals("Invoice Systemic for the previous month", task2.getContent());
        assertFalse(task2.isOverdue());
        assertEquals("29/06/2012", new DateTime(task2.getDueDate()).toString("dd/MM/yyyy"));
        assertFalse(task2.hasTags());

        Task task3 = parser.parseLine(lines[3]);
        assertNotNull(task3);
        assertEquals("Check out JDBI", task3.getContent());
        assertFalse(task3.isOverdue());
        assertNull(task3.getDueDate());
        assertTrue(task3.hasTags());
        assertEquals(1, task3.getTags().size());
        assertEquals("url(http://www.jdbi.org/archive.html)", task3.getTags().get(0).getContent());

        Task task4 = parser.parseLine(lines[4]);
        assertNotNull(task4);
        assertEquals("Mow lawn", task4.getContent());
        assertTrue(task4.isOverdue());
        assertNull(task4.getDueDate());
        assertFalse(task4.hasTags());

        Task task5 = parser.parseLine(lines[5]);
        assertNotNull(task5);
        assertEquals("Install blinds throughout the house", task5.getContent());
        assertFalse(task5.isOverdue());
        assertEquals(new DateTime().plusDays(1).toString("dd/MM/yyyy"), new DateTime(task5.getDueDate()).toString("dd/MM/yyyy"));
        assertTrue(task5.hasTags());
        assertEquals(1, task5.getTags().size());
        assertEquals("rennovation", task5.getTags().get(0).getContent());
    }
}
