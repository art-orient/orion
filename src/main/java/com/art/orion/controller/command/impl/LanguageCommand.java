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

public class LanguageCommand implements Command {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public String execute(HttpServletRequest req) {
        String selectedLanguage = req.getParameter(LANGUAGE);
        req.getSession().setAttribute(LANGUAGE, selectedLanguage);
        logger.log(Level.INFO, "change of language");
        ErrorMessageManager.setLocale(selectedLanguage);
        String page = (String) req.getSession().getAttribute(CURRENT_PAGE);
//        todo
//        if (page == null) {
//            page = ConfigManager.getProperty("page.index");
//        }
//        String previousPage = page.substring(4, page.length() - 4);
//        if (ACCESSORIES.equals(previousPage) || CLOTHING.equals(previousPage) || SHOES.equals(previousPage)) {
//            page = ConfigManager.getProperty("page.index");
//        }
        if (ERROR_PAGE.equals(page)) {
            CommandFactory.sendPageNotFound(req);
        }
        page = INDEX_PAGE;
        return page;
    }
}
