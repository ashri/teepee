package com.threeheadedmonkey.teepee.parser;

import com.threeheadedmonkey.teepee.entity.Tag;
import com.threeheadedmonkey.teepee.entity.Task;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Parse a Task line with its tags and due date
 */
public class TaskParser implements Parser<Task> {

    @Override
    public Pattern getPattern() {
        return Pattern.compile("^[ |\t]*- ([^ ][^@]+) ?(@.+)?$");
    }

    @Override
    public Task parseLine(String line) {
        Matcher match = getPattern().matcher(line);

        if (!match.find()) {
            return null;
        }

        // Get the content from the line
        String content = match.group(1);
        Task task = new Task(content.trim());

        // Check for a due date or tags
        if (match.group(2) != null) {
            List<String> tags = matchTags(match.group(2));
            for (String tag : tags) {
                addTag(task, tag);
            }
        }

        return task;
    }

    private List<String> matchTags(String group) {
        List<String> tags = new ArrayList<String>();
        Matcher match = Pattern.compile("@([^ ]+)").matcher(group);
        while (match.find()) {
            tags.add(match.group().substring(1));
        }
        return tags;
    }

    private void addTag(Task task, String tag) {
        if ("overdue".equals(tag)) {
            task.setOverdue();
        } else if ("today".equals(tag)) {
            task.setDueDate(new Date());
        } else if ("tomorrow".equals(tag)) {
            task.setDueDate(new DateTime().plusDays(1).toDate());
        } else if (tag.startsWith("due")) {
            String dateString = tag.substring(4, tag.length() - 1);
            task.setDueDate(DateTimeFormat.forPattern("yyyy-MM-dd").parseDateTime(dateString).toDate());
        } else {
            task.addTag(new Tag(tag.trim()));
        }

    }
}
