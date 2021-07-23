package com.art.orion.controller.command.util;

/**
 * The utility is responsible for xss protection
 *
 * @author Aliaksandr Artsikhovich
 */
public class XssProtection {
    private static final String LEFT_BRACKET = "<";
    private static final String RIGHT_BRACKET = ">";
    private static final String LESS_THAN = "&lt";
    private static final String GREATER_THAN = "&gt";

    private XssProtection() {
    }

    /**
     * Corrects text by filtering angle brackets
     *
     * @param text {@link String} the text to be corrected
     * @return {@link String} corrected text
     */
    public static String replaceBrackets(String text) {
        String answer;
        if (text == null) {
            answer = "";
        } else {
            answer = text.replace(LEFT_BRACKET, LESS_THAN).replace(RIGHT_BRACKET, GREATER_THAN);
        }
        return answer;
    }
}
