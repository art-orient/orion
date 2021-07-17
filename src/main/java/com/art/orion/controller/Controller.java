package com.art.orion.controller;

import com.art.orion.controller.command.Command;
import com.art.orion.controller.command.CommandFactory;
import com.art.orion.controller.command.PagePath;
import jakarta.servlet.annotation.MultipartConfig;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.art.orion.util.Constant.CURRENT_PAGE;

@WebServlet(urlPatterns = "/controller")
@MultipartConfig(fileSizeThreshold = 1024 * 1024,
                maxFileSize = 1024 * 1024 * 10,
                maxRequestSize = 1024 * 1024 * 50)
public class Controller extends HttpServlet {
    private static final Logger logger = LogManager.getLogger();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        logger.log(Level.DEBUG, "call of method doGet of class Controller");
        processRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        logger.log(Level.DEBUG, "call of method doPost of class Controller");
        processRequest(req, resp);
    }

    private void processRequest(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        Command command = CommandFactory.defineCommand(req);
        String page = command.execute(req);
        req.getSession().setAttribute(CURRENT_PAGE, page);
        if (page != null) {
            logger.log(Level.DEBUG, "forward on page = {}", page);
            req.getRequestDispatcher(page).forward(req, resp);
        } else {
            req.getRequestDispatcher(PagePath.ERROR_PAGE).forward(req, resp);
        }
    }
}
