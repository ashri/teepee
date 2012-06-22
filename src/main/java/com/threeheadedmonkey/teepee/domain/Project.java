package com.threeheadedmonkey.teepee.domain;

import java.util.ArrayList;
import java.util.List;

public class Project extends ParentItem {


    public Project(String content) {
        super(content);
    }

    public String toString() {
        return super.toString() + ":";
    }
}
