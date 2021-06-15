package com.art.orion.controller;

import com.art.orion.controller.command.Command;
import com.art.orion.controller.command.CommandFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/controller")
public class Controller extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        processRequest(req, resp);
    }


    private void processRequest(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Command command = CommandFactory.defineCommand(req);
        String page = command.execute(req);
        System.out.println(page);
        if (page != null) {
            req.getRequestDispatcher(page).forward(req, resp);
        }
//        else {
//            req.setAttribute(REQUEST_ERROR_CODE,
//                    MessageManager.getMessage("msg.errorCode404"));
//            req.setAttribute(REQUEST_ERROR_MESSAGE,
//                    MessageManager.getMessage("msg.errorMessage404"));
//            req.getRequestDispatcher(ConfigurationManger.getProperty("page.error"))
//                    .forward(req, resp);
//        }
    }
}