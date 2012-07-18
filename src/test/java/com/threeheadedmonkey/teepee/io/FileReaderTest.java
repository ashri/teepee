package com.threeheadedmonkey.teepee.io;

import com.threeheadedmonkey.teepee.entity.Consolidator;
import com.threeheadedmonkey.teepee.entity.Item;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Collection;

import static org.junit.Assert.*;

/**
 * Unit test for the FileReader
 */
public class FileReaderTest {

    @Test
    public void testRead() throws Exception {
        Collection<Item> items = readFile();
        assertEquals(17, items.size());
    }

    @Test
    public void testReadAndConsolidate() throws Exception {
        Collection<Item> items = readFile();
        Collection<Item> consolidatedItems = new Consolidator(items).consolidate();
        assertEquals(3, consolidatedItems.size());
    }

    private Collection<Item> readFile() throws FileNotFoundException {
        File testFile = new File("src/test/resources/Personal-output.taskpaper");
        assertTrue(testFile.exists());
        Collection<Item> items = new FileReader().read(new java.io.FileReader(testFile));
        assertNotNull(items);
        return items;
    }

}
