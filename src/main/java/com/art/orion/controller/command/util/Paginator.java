package com.art.orion.controller.command.util;

import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Objects;

import static com.art.orion.util.Constant.PAGE;
import static com.art.orion.util.Constant.OFFSET;

public class Paginator {
    private static final Logger logger = LogManager.getLogger();
    public static final int LIMIT = 5;

    private Paginator() {
    }

    public static int getCurrentPage(HttpServletRequest request) {
        int requestPageNumber = RequestParseNumberHelper.getInt(request, PAGE);
        if (requestPageNumber == 0) {
            Integer sessionPageNumber;
            try {
                sessionPageNumber = (Integer) request.getSession().getAttribute(PAGE);
            } catch (ClassCastException e) {
                logger.log(Level.ERROR, e);
                sessionPageNumber = 1;
            }
            requestPageNumber = Objects.requireNonNullElse(sessionPageNumber, 1);
        }
        return requestPageNumber;
    }

    public static int  preparePagination(HttpServletRequest req) {
        int pageNumber = getCurrentPage(req);
        req.getSession().setAttribute(PAGE, pageNumber);
        int offset = getOffset(pageNumber);
        req.setAttribute(OFFSET, offset);
        return offset;
    }

    public static int getOffset(int pageNumber) {
        int offset = 0;
        if (pageNumber > 1) {
            offset = LIMIT * (pageNumber - 1);
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
}
