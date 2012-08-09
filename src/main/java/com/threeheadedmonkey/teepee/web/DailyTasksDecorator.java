package com.threeheadedmonkey.teepee.web;

import com.threeheadedmonkey.teepee.entity.Item;

import java.util.Collection;

/**
 * Decorate tasks to provide getters for today and tomorrow
 */
public class DailyTasksDecorator {

    private final Collection<Item> items;

    public DailyTasksDecorator(Collection<Item> items) {
        this.items = items;
    }

    /**
     * @return the tasks which are overdue
     */
    public boolean hasOverdue() {
        for (Item item : items) {

        }
        return false;
    }

    public Collection<Item> getOverdue() {
        return null;
    }

    /**
     * @return the tasks which are due today
     */
    public boolean hasToday() {
        return false;
    }

    public Collection<Item> getToday() {
        return null;
    }

    public boolean hasTomorrow() {
        return false;
    }

    public Collection<Item> getTomorrow() {
        return null;
    }

    public boolean hasRegularTasks() {
        return false;
    }

    public Collection<Item> getRegularTasks() {
        return null;
    }

}
