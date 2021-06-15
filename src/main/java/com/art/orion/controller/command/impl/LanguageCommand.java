package com.art.orion.controller.command.impl;

import com.art.orion.controller.command.Command;
import com.art.orion.util.ConfigManager;
import com.art.orion.util.MessageManager;

import javax.servlet.http.HttpServletRequest;

public class LanguageCommand implements Command {
    private static final String LANGUAGE = "language";

    @Override
    public String execute(HttpServletRequest req) {
        String selectedLanguage = req.getParameter(LANGUAGE);
        req.getSession().setAttribute(LANGUAGE, selectedLanguage);
//        MessageManager.setLocale(selectedLanguage);
//        return ConfigManager.getProperty("page.index");
        return "index.jsp";
    }
}
