package com.art.orion.controller.command.impl;

import com.art.orion.controller.command.Command;
import com.art.orion.util.ErrorMessageManager;

import javax.servlet.http.HttpServletRequest;

public class LanguageCommand implements Command {
    private static final String LANGUAGE = "language";
    private static final String CURRENT_PAGE = "current_page";

    @Override
    public String execute(HttpServletRequest req) {
        String selectedLanguage = req.getParameter(LANGUAGE);
        req.getSession().setAttribute(LANGUAGE, selectedLanguage);
        ErrorMessageManager.setLocale(selectedLanguage);
        String page = (String) req.getSession().getAttribute(CURRENT_PAGE);
        if (page == null) {
            page = "index.jsp";
        }
        return page;
    }
}
