package com.art.orion.controller;

import com.art.orion.controller.command.Command;
import com.art.orion.controller.command.CommandFactory;
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

@WebServlet("/controller")
@MultipartConfig(fileSizeThreshold = Controller.MB1,
                maxFileSize = Controller.MB10,
                maxRequestSize = Controller.MB50)
public class Controller extends HttpServlet {
    private static final Logger logger = LogManager.getLogger();
    protected static final int MB1 = 1024 * 1024;
    protected static final int MB10 = 1024 * 1024 * 10;
    protected static final int MB50 = 1024 * 1024 * 50;

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
        logger.log(Level.DEBUG, () -> String.format("parameters value = %s", req.getParameterNames()));
        Command command = CommandFactory.defineCommand(req);
        String page = command.execute(req);
        req.getSession().setAttribute(CURRENT_PAGE, page);
        if (page != null) {
            logger.log(Level.DEBUG, "forward on page = {}", page);
            req.getRequestDispatcher(page).forward(req, resp);
        }
    }
}
