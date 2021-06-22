package com.art.orion.controller.command.impl;

import com.art.orion.controller.command.Command;
import com.art.orion.util.ConfigManager;

import javax.servlet.http.HttpServletRequest;

import static com.art.orion.util.Constant.REGISTRATION_STATUS;

public class CheckRegStatusCommand implements Command {

    @Override
    public String execute(HttpServletRequest req) {
        String registrationStatus = req.getParameter(REGISTRATION_STATUS);
        if (registrationStatus == null) {
            registrationStatus = "";
        }
        req.getSession().setAttribute(REGISTRATION_STATUS, registrationStatus);
        return ConfigManager.getProperty("page.registration");
    }
}
