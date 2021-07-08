package com.art.orion.controller.command.impl;

import com.art.orion.controller.command.Command;
import com.art.orion.controller.command.util.Paginator;
import com.art.orion.model.entity.Accessory;
import com.art.orion.model.entity.Shoes;
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
import static com.art.orion.util.Constant.OFFSET;
import static com.art.orion.util.Constant.PAGE;
import static com.art.orion.util.Constant.PRODUCTS;

public class ShoesCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    private final static String DIRECTORY = "/images/shoes/";

    @Override
    public String execute(HttpServletRequest req) {
        logger.log(Level.DEBUG,"Go to page shoes");
        HttpSession session = req.getSession();
        int pageNumber = Paginator.getCurrentPage(req);
        session.setAttribute(PAGE, pageNumber);
        int offset = Paginator.getOffset(pageNumber);
        List<Shoes> products = ProductService.searchShoes(LIMIT, offset);
        session.setAttribute(PRODUCTS, products);
        int numberProducts = ProductService.countNumberShoes();
        req.setAttribute(NUMBER_PRODUCTS, numberProducts);
        req.setAttribute(OFFSET, offset);
        int numberPages = Paginator.findNumberPages(numberProducts);
        req.setAttribute(NUMBER_PAGES, numberPages);
        req.setAttribute(CURRENT_COMMAND, req.getParameter(COMMAND));
        return ConfigManager.getProperty("page.shoes");
    }
}
