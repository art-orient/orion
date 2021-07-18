package com.art.orion.controller.command.impl;

import com.art.orion.controller.command.Command;
import com.art.orion.controller.command.CommandFactory;
import com.art.orion.util.ErrorMessageManager;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import jakarta.servlet.http.HttpServletRequest;

import static com.art.orion.controller.command.PagePath.ERROR_PAGE;
import static com.art.orion.controller.command.PagePath.INDEX_PAGE;
import static com.art.orion.util.Constant.LANGUAGE;
import static com.art.orion.util.Constant.CURRENT_PAGE;

/**
 * The command is responsible for changing the interface language
 *
 * @author Aliaksandr Artsikhovich
 * @see Command
 */
public class LanguageCommand implements Command {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public String execute(HttpServletRequest req) {
        String selectedLanguage = req.getParameter(LANGUAGE);
        req.getSession().setAttribute(LANGUAGE, selectedLanguage);
        logger.log(Level.INFO, "change of language");
        ErrorMessageManager.setLocale(selectedLanguage);
        String page = (String) req.getSession().getAttribute(CURRENT_PAGE);
        if (ERROR_PAGE.equals(page)) {
            CommandFactory.sendPageNotFound(req);
        }
        page = INDEX_PAGE;
        return page;
    }
}
