package com.art.orion.controller.command.impl;

import com.art.orion.controller.command.Command;
import com.art.orion.controller.command.util.Paginator;
import com.art.orion.model.entity.ProductCategory;
import com.art.orion.model.entity.Role;
import com.art.orion.model.entity.Shoes;
import com.art.orion.model.service.ProductService;
import com.art.orion.exception.ServiceException;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

import static com.art.orion.controller.command.PagePath.SHOES_PAGE;
import static com.art.orion.controller.command.util.Paginator.LIMIT;
import static com.art.orion.util.Constant.NUMBER_PAGES;
import static com.art.orion.util.Constant.NUMBER_PRODUCTS;
import static com.art.orion.util.Constant.PAGE;
import static com.art.orion.util.Constant.PRODUCTS;
import static com.art.orion.util.Constant.ROLE;

/**
 * The command is responsible for the formation of the list of shoes
 *
 * @author Aliaksandr Artsikhovich
 * @see Command
 */
public class ShoesCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    private final ProductService productService;

    public ShoesCommand(ProductService productService) {
        this.productService = productService;
    }

    @Override
    public String execute(HttpServletRequest req) {
        logger.log(Level.DEBUG,"Go to page shoes");
        String pageNumber = req.getParameter(PAGE);
        req.getSession().setAttribute(PAGE, pageNumber);
        int offset = Paginator.preparePagination(req);
        try {
            String role = (String) req.getSession().getAttribute(ROLE);
            boolean isAdmin = Role.ADMIN.name().equals(role);
            List<Shoes> products = productService.searchShoes(LIMIT, offset, isAdmin);
            req.getSession().setAttribute(PRODUCTS, products);
            int numberProducts = productService.countNumberProducts(ProductCategory.SHOES, isAdmin);
            req.setAttribute(NUMBER_PRODUCTS, numberProducts);
            int numberPages = Paginator.findNumberPages(numberProducts);
            req.setAttribute(NUMBER_PAGES, numberPages);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, "Database access error when searching for shoes", e);
        }
        return SHOES_PAGE;
    }
}
