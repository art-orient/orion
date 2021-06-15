package com.art.orion.controller.command;

import javax.servlet.http.HttpServletRequest;

public class CommandFactory {
    private static final String COMMAND = "command";

    private CommandFactory() {
    }

    public static Command defineCommand(HttpServletRequest req) {
        String action = req.getParameter(COMMAND);
        Command command = null;                         // not null
//        = new EmptyCommand();
        if (action == null || action.isEmpty()) {
//            sendPageNotFound(req);
            return command;
        }
        try {
            command = TypeCommand.valueOf(action.toUpperCase()).getCommand();
        } catch (IllegalArgumentException e) {
//            sendPageNotFound(req);
        }
        return command;
    }

//    private static void sendPageNotFound(HttpServletRequest req) {
//        req.setAttribute(REQUEST_ERROR_CODE,
//                MessageManager.getMessage("msg.errorCode404"));
//        req.setAttribute(REQUEST_ERROR_MESSAGE,
//                MessageManager.getMessage("msg.errorMessage404"));
//    }
}
