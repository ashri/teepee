package com.threeheadedmonkey.teepee.parser;

import com.threeheadedmonkey.teepee.domain.Project;

import java.util.regex.Pattern;

/**
 * Parse a Project line
 */
public class ProjectParser implements Parser<Project> {

    @Override
    public Pattern getPattern() {
        return Pattern.compile("^.+:$");
    }

    @Override
    public Project parseLine(String line) {
        if (line == null || line.isEmpty()) {
            return null;
        }
        return new Project(line.substring(0, line.length() - 1).trim());
    }
}
