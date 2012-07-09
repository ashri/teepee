package com.threeheadedmonkey.teepee.entity;

/**
 * Basic item which contains a line of content
 */
public class Item {

    private final String content;

    public Item(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public boolean hasContent() {
        return content != null && !content.isEmpty();
    }

    public String toString() {
        return content;
    }
}
