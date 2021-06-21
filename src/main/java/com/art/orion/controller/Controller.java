package com.art.orion.controller;

import com.art.orion.controller.command.Command;
import com.art.orion.controller.command.CommandFactory;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.art.orion.util.Constant.CURRENT_PAGE;

@WebServlet("/controller")
public class Controller extends HttpServlet {
    static Logger logger = LogManager.getLogger();

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
            logger.log(Level.DEBUG, "forward on page = " + page);
            req.getRequestDispatcher(page).forward(req, resp);
        }
    }
}
