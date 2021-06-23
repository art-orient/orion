package com.art.orion.controller.command.impl;

import com.art.orion.controller.command.Command;
import com.art.orion.controller.command.CommandFactory;
import com.art.orion.util.ConfigManager;
import com.art.orion.util.ErrorMessageManager;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

import static com.art.orion.util.Constant.LANGUAGE;
import static com.art.orion.util.Constant.CURRENT_PAGE;

public class LanguageCommand implements Command {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public String execute(HttpServletRequest req) {
        String selectedLanguage = req.getParameter(LANGUAGE);
        req.getSession().setAttribute(LANGUAGE, selectedLanguage);
        ErrorMessageManager.setLocale(selectedLanguage);
        String page = (String) req.getSession().getAttribute(CURRENT_PAGE);
        if (page == null) {
            page = ConfigManager.getProperty("page.index");
        }
        if (ConfigManager.getProperty("page.error").equals(page)) {
            CommandFactory.sendPageNotFound(req);
        }
        logger.log(Level.INFO, "change of language");
        return page;
    }
}
