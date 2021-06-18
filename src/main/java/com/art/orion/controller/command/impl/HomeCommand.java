package com.art.orion.controller.command.impl;

import com.art.orion.controller.command.Command;

import javax.servlet.http.HttpServletRequest;

public class HomeCommand implements Command {
    @Override
    public String execute(HttpServletRequest req) {
        return "index.jsp";
    }
}
