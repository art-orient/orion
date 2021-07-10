package com.art.orion.controller.command.impl;

import com.art.orion.controller.command.Command;
import com.art.orion.controller.command.util.Paginator;
import com.art.orion.model.entity.Clothing;
import com.art.orion.model.service.ProductService;
import com.art.orion.util.ConfigManager;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

import static com.art.orion.controller.command.util.Paginator.LIMIT;
import static com.art.orion.util.Constant.COMMAND;
import static com.art.orion.util.Constant.CURRENT_COMMAND;
import static com.art.orion.util.Constant.NUMBER_PAGES;
import static com.art.orion.util.Constant.NUMBER_PRODUCTS;
import static com.art.orion.util.Constant.PRODUCTS;

public class ClothingCommand implements Command {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public String execute(HttpServletRequest req) {
        logger.log(Level.DEBUG,"Go to page clothing");
        HttpSession session = req.getSession();
        int offset = Paginator.preparePagination(req);
        List<Clothing> clothing = ProductService.searchClothing(LIMIT, offset);
        session.setAttribute(PRODUCTS, clothing);
        int numberProducts = ProductService.countNumberClothing();
        req.setAttribute(NUMBER_PRODUCTS, numberProducts);
        int numberPages = Paginator.findNumberPages(numberProducts);
        req.setAttribute(NUMBER_PAGES, numberPages);
        req.setAttribute(CURRENT_COMMAND, req.getParameter(COMMAND));
        return ConfigManager.getProperty("page.clothing");
    }
}
