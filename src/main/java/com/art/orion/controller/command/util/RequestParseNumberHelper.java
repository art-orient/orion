package com.art.orion.controller.command.util;

import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;

import static com.art.orion.util.Constant.COST;

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

    public static BigDecimal getBigDecimal(HttpServletRequest request, String parameter) {
        BigDecimal cost = BigDecimal.ZERO;
        try {
            cost = BigDecimal.valueOf(Double.parseDouble(request.getParameter(parameter)));
        } catch (NumberFormatException e) {
            logger.log(Level.ERROR, () -> "This parameter is not BigDecimal - " + request.getParameter(COST));
        }
        return cost;
    }
}
