package com.art.orion.controller.command.impl;

import com.art.orion.controller.command.Command;
import com.art.orion.controller.command.CommandFactory;
import com.art.orion.util.ConfigManager;
import com.art.orion.util.ErrorMessageManager;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import jakarta.servlet.http.HttpServletRequest;

import static com.art.orion.util.Constant.ACCESSORIES;
import static com.art.orion.util.Constant.COMMAND;
import static com.art.orion.util.Constant.CURRENT_COMMAND;
import static com.art.orion.util.Constant.LANGUAGE;
import static com.art.orion.util.Constant.CURRENT_PAGE;

public class LanguageCommand implements Command {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public String execute(HttpServletRequest req) {
        String selectedLanguage = req.getParameter(LANGUAGE);
        req.getSession().setAttribute(LANGUAGE, selectedLanguage);
        logger.log(Level.INFO, "change of language");
        ErrorMessageManager.setLocale(selectedLanguage);
        String page = (String) req.getSession().getAttribute(CURRENT_PAGE);
        String previousPage = page.substring(4, page.length() - 4);
        if (page == null || previousPage.equals(ACCESSORIES)) {
            page = ConfigManager.getProperty("page.index");
        }
        if (ConfigManager.getProperty("page.error").equals(page)) {
            CommandFactory.sendPageNotFound(req);
        }
        return page;
    }
}
