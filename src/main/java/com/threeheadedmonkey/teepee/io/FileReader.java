package com.threeheadedmonkey.teepee.io;

import com.google.common.base.Splitter;
import com.google.common.io.CharStreams;
import com.threeheadedmonkey.teepee.entity.Item;
import com.threeheadedmonkey.teepee.exception.TeepeeException;
import com.threeheadedmonkey.teepee.parser.Parser;
import com.threeheadedmonkey.teepee.parser.ParserFactory;

import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Read a TaskPaper file into an entity
 */
public class FileReader {

    /**
     * Read the content from the Reader into a Collection of Items
     *
     * @param content The content to read in, parse and return
     * @return the parsed Collection of Items
     */
    public Collection<Item> read(Reader content) {
        String readerContent = null;
        try {
            readerContent = CharStreams.toString(content);
        } catch (IOException e) {
            throw new TeepeeException("Failed to read content from Reader", e);
        }
        return read(readerContent);
    }

    /**
     * Read the content from the String into a Collection of Items
     *
     * @param content The content to read in, parse and return
     * @return the parsed Collection of Items
     */
    public Collection<Item> read(String content) {
        // Split the file into single lines which can then be parsed
        Iterable<String> lines = Splitter.on("\r?\n").trimResults().omitEmptyStrings().split(content);
        // Parse the lines into a single Collection and return it
        Collection<Item> flatItems = parseLines(lines);
        // Consolidate items into a tree
        return consolidateItems(flatItems);
    }

    /**
     * Consolidate a collection of flat items into a tree of items where Tasks fall under Projects and so on
     *
     * @param flatItems the collection of items to consolidate
     * @return the tree of items
     */
    protected Collection<Item> consolidateItems(Collection<Item> flatItems) {
        return null;
    }

    /**
     * Parse each line into a Collection of items
     *
     * @param lines The lines to parse
     * @return the collection of items
     */
    private Collection<Item> parseLines(Iterable<String> lines) {
        List<Item> items = new ArrayList<Item>();
        for (String line : lines) {
            Item item = parseLine(line);
            if (item != null) {
                items.add(item);
            }
        }
        return items;
    }

    private Item parseLine(String line) {
        if (line == null || line.isEmpty()) {
            throw new IllegalArgumentException("Line to parse was empty");
        }

        Parser parser = ParserFactory.getParser(line);
        if (parser != null) {
            return parser.parseLine(line);
        }
        return null;
    }

}
