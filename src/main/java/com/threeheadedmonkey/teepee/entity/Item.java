package com.threeheadedmonkey.teepee.entity;

/**
 * Basic item which contains a line of content
 */
public class Item {

    private final String content;
    private int level;

    public Item(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public boolean hasContent() {
        return content != null && !content.isEmpty();
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getLevel() {
        return level;
    }

    public String toString() {
        return content;
    }
}
