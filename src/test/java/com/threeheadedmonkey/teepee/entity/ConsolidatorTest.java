package com.threeheadedmonkey.teepee.entity;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Unit test for the Consolidator
 */
public class ConsolidatorTest {

    private Collection<Item> items;

    @Before
    public void setUp() throws Exception {
        items = new ArrayList<Item>();
        items.add(new Project("Project 1"));
        items.add(new Task("Task 1"));
        items.add(new Project("Project 2"));
        items.add(new Task("Task 2"));
        items.add(new Task("Note 1"));
        items.add(new Task("Task 3"));
        items.add(new Project("Project 3"));
        items.add(new Task("Task 4"));
    }

    @Test
    public void testConsolidate() throws Exception {
        Collection<Item> consolidatedItems = new Consolidator(items).consolidate();
        assertNotNull(consolidatedItems);
        assertEquals(3, consolidatedItems.size());

        Iterator<Item> itIt = consolidatedItems.iterator();

        Item i0 = itIt.next();
        assertEquals(Project.class, i0.getClass());

        Item i1 = itIt.next();
        assertEquals(Project.class, i1.getClass());

        Item i2 = itIt.next();
        assertEquals(Project.class, i2.getClass());

    }
}
