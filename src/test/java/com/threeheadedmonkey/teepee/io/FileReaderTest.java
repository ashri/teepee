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
public class FileReaderTest extends FileTest {

    private static final String location = "src/test/resources/Personal-output.taskpaper";

    @Test
    public void testRead() throws Exception {
        Collection<Item> items = readFile(location);
        assertEquals(17, items.size());
    }

    @Test
    public void testReadAndConsolidate() throws Exception {
        Collection<Item> items = readFile(location);
        Collection<Item> consolidatedItems = new Consolidator(items).consolidate();
        assertEquals(3, consolidatedItems.size());
    }

}
