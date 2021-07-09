package com.art.orion.controller.command.util;

import java.util.Arrays;
import java.util.List;

public class TextHandler {

    private TextHandler() {
    }

    public static String createTextFromList(List<String> description) {
        StringBuilder text = new StringBuilder();
        for (String s : description) {
            text.append(s).append("\n");
        }
        text.setLength(text.length() - 1);
        return text.toString();
    }

    public static List<String> createListFromText(String text) {
        return Arrays.asList(text.split("[\r?\n]"));
    }
}
