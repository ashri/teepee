package com.threeheadedmonkey.teepee.entity;

public class Project extends ParentItem {


    public Project(String content) {
        super(content);
    }

    public String toString() {
        return super.toString() + ":";
    }
}
