package com.threeheadedmonkey.teepee.entity;

import java.util.Collection;

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
        return items;
    }
}
