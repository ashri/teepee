package com.threeheadedmonkey.teepee.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Task extends ParentItem {

    private final List<Tag> tags;
    private boolean overdue;
    private Date dueDate;

    public Task(String content) {
        super(content);
        this.tags = new ArrayList<Tag>();
    }

    public void addTag(Tag tag) {
        tags.add(tag);
    }

    public List<Tag> getTags() {
        return tags;
    }

    public boolean hasTags() {
        return tags != null && !tags.isEmpty();
    }

    public void setOverdue() {
        this.overdue = true;
    }

    public boolean isOverdue() {
        return overdue;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public boolean hasDueDate() {
        return dueDate != null;
    }

    public boolean isDueToday() {
        return false;
    }

    public boolean isDueTomorrow() {
        return false;
    }

}
