package com.art.orion.controller;

import com.art.orion.model.validator.EmailValidator;
import com.art.orion.model.validator.StringValidator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "CustomerController", urlPatterns = "/registration")
public class CustomerController extends HttpServlet {
    static Logger logger = LogManager.getLogger();

    @Override
    protected void doPost(
            HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        RequestCustomer customer = RequestCustomer.fromRequestParameters(request);
        customer.setAsRequestAttributes(request);
        List violations = customer.validate();

        if (!violations.isEmpty()) {
            request.setAttribute("violations", violations);
        }

        String url = determineUrl(violations);
        logger.log(Level.INFO, "работа в методе doPost");
        request.getRequestDispatcher(url).forward(request, response);
    }

    private String determineUrl(List violations) {
        if (!violations.isEmpty()) {
            return "/";
        } else {
            logger.log(Level.INFO, "работа в методе determineUrl");
            return "/WEB-INF/views/customerinfo.jsp";
        }
    }

    private static class RequestCustomer {

        private final String firstName;
        private final String lastName;
        private final String email;

        private RequestCustomer(String firstName, String lastName, String email) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.email = email;
        }

        public static RequestCustomer fromRequestParameters(
                HttpServletRequest request) {
            return new RequestCustomer(
                    request.getParameter("firstname"),
                    request.getParameter("lastname"),
                    request.getParameter("email"));
        }

        public void setAsRequestAttributes(HttpServletRequest request) {
            request.setAttribute("firstname", firstName);
            request.setAttribute("lastname", lastName);
            request.setAttribute("email", email);
        }

        public List validate() {
            List violations = new ArrayList<>();
            if (!StringValidator.validate(firstName)) {
                violations.add("Имя является обязательным полем");
            }
            if (!StringValidator.validate(lastName)) {
                violations.add("Фамилия является обязательным полем");
            }
            if (!EmailValidator.validate(email)) {
                violations.add("Email должен быть правильно сформирован");
            }
            return violations;
        }
    }
}