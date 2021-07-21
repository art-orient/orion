package com.art.orion.controller.command;

import jakarta.servlet.http.HttpServletRequest;

/**
 * The {@code Command} interface represents command.
 *
 * @author Aliaksandr Artsikhovich
 * @version 1.0
 */
public interface Command {

    /**
     * Execute command.
     *
     * @param req {@link HttpServletRequest} the request
     * @return {@link String} the string containing page path
     */
    String execute(HttpServletRequest req);
}
