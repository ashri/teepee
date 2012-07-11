package com.threeheadedmonkey.teepee.io;

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
        assertEquals(13, items.size());
    }

    private Collection<Item> readFile() throws FileNotFoundException {
        File testFile = new File("src/test/resources/Personal-output.taskpaper");
        assertTrue(testFile.exists());
        Collection<Item> items = new FileReader().read(new java.io.FileReader(testFile));
        assertNotNull(items);
        return items;
    }

}
