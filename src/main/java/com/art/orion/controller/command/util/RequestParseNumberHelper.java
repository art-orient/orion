package com.art.orion.controller.command.util;

import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RequestParseNumberHelper {
    private static final Logger logger = LogManager.getLogger();

    private RequestParseNumberHelper() {
    }

    public static int getInt(HttpServletRequest request, String parameter) {
        int number = 0;
        try {
            number = Integer.parseInt(request.getParameter(parameter));
        } catch (NumberFormatException e) {
            logger.log(Level.ERROR, e);
        }
        return number;
    }
}
