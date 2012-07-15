package com.threeheadedmonkey.teepee.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Consolidate a list of flat items into a project/task/note hierarchy
 */
public class Consolidator {

    private final Collection<Item> items;


    /**
     * Construct Consolidator with provided items
     *
     * @param items the collection of items to consolidate
     */
    public Consolidator(Collection<Item> items) {
        this.items = items;
    }

    /**
     * Consolidate a collection of flat items into a tree of items where Tasks fall under Projects and so on
     *
     * @return the tree of items
     */
    public Collection<Item> consolidate() {

        // We will be returning a new list
        List<Item> cItems = new ArrayList<Item>();

        // Loop over our existing (flat) items looking for level changes while recording the last Item which
        // can act as a parent
        ParentItem lastParent = null;
        int currentLevel = 0;
        for (Item item : items) {
            // TODO complete consolidation
            cItems.add(item);
        }

        return cItems;
    }
}
