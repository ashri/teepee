package com.threeheadedmonkey.teepee.io;

import com.threeheadedmonkey.teepee.entity.Item;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Collection;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Superclass for file based tests
 */
public abstract class FileTest {

    protected Collection<Item> readFile(String location) throws FileNotFoundException {
        File testFile = new File(location);
        assertTrue(testFile.exists());
        Collection<Item> items = new FileReader().read(new java.io.FileReader(testFile));
        assertNotNull(items);
        return items;
    }

}
