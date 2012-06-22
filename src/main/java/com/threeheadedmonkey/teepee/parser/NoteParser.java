package com.threeheadedmonkey.teepee.parser;

import com.threeheadedmonkey.teepee.domain.Note;

import java.util.regex.Pattern;

/**
 * Parse a note line
 */
public class NoteParser implements Parser<Note> {

    @Override
    public Pattern getPattern() {
        return Pattern.compile("^.+[^:]$");
    }

    @Override
    public Note parseLine(String line) {
        if (line == null || line.isEmpty()) {
            return null;
        }
        return new Note(line.trim());
    }
}
