package com.art.orion.controller.command.util;

import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static com.art.orion.util.Constant.COMMAND;
import static com.art.orion.util.Constant.CURRENT_COMMAND;
import static com.art.orion.util.Constant.PAGE;
import static com.art.orion.util.Constant.OFFSET;
import static com.art.orion.util.Constant.USER_MANAGEMENT;

/**
 * The utility is responsible for pagination
 *
 * @author Aliaksandr Artsikhovich
 */
public class Paginator {
    private static final Logger logger = LogManager.getLogger();
    public static final int LIMIT = 5;
    public static final int USER_LIMIT = 10;

    private Paginator() {
    }

    public static int getCurrentPage(HttpServletRequest request) {
        int sessionPageNumber;
        String pageNumber = (String) request.getSession().getAttribute(PAGE);
            try {
                if (pageNumber == null) {
                    sessionPageNumber = 1;
                } else {
                    sessionPageNumber = Integer.parseInt(pageNumber);
                }
            } catch (ClassCastException e) {
                logger.log(Level.ERROR, e);
                sessionPageNumber = 1;
            }
        return sessionPageNumber;
    }

    public static int  preparePagination(HttpServletRequest req) {
        int pageNumber = getCurrentPage(req);
        req.getSession().setAttribute(PAGE, pageNumber);
        String currentCommand = req.getParameter(COMMAND);
        req.setAttribute(CURRENT_COMMAND, currentCommand);
        int offset = getOffset(pageNumber, currentCommand);
        req.setAttribute(OFFSET, offset);
        return offset;
    }

    public static int getOffset(int pageNumber, String command) {
        int offset = 0;
        if (pageNumber > 1) {
            if (USER_MANAGEMENT.equals(command)) {
                offset = USER_LIMIT * (pageNumber - 1);
            } else {
                offset = LIMIT * (pageNumber - 1);
            }
        }
        return offset;
    }

    public static int findNumberPages(int numberProducts) {
        int numberPages = 0;
        if (numberProducts > 0) {
            numberPages = numberProducts / LIMIT;
        }
        return numberProducts % LIMIT > 0 ? numberPages + 1: numberPages;
    }

    public static int findUserNumberPages(int numberUsers) {
        int numberPages = 0;
        if (numberUsers > 0) {
            numberPages = numberUsers / USER_LIMIT;
        }
        return numberUsers % USER_LIMIT > 0 ? numberPages + 1: numberPages;
    }
}
