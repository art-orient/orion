package com.art.orion.controller.filter;

import com.art.orion.util.ConfigManager;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

import static com.art.orion.util.Constant.USERNAME;

@WebFilter(urlPatterns = "/*")
public class AuthenticationFilter implements Filter {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String action = request.getParameter("command");
        if (isForbiddenAction(action) && isUnauthorizedUser(request)) {
            logger.log(Level.WARN, () -> String.format("Forbidden page for unauthorized user - %s", action));
            request.getRequestDispatcher(ConfigManager.getProperty("page.login")).forward(request, servletResponse);
        }
        filterChain.doFilter(request, servletResponse);
    }

    private boolean isForbiddenAction(String action) {
        return action != null && isForbiddenPage(action);
    }

    private boolean isForbiddenPage(String action) {
        boolean isForbiddenPage = false;
        for (String page : ForbiddenPage.getAuthenticatedUserPages()) {
            if (action.equalsIgnoreCase(page)) {
                isForbiddenPage = true;
                break;
            }
        }
        for (String page : ForbiddenPage.getAdminPages()) {
            if (action.equalsIgnoreCase(page)) {
                isForbiddenPage = true;
                break;
            }
        }
        return isForbiddenPage;
    }

    private boolean isUnauthorizedUser(HttpServletRequest request) {
        return request.getSession().getAttribute(USERNAME) == null;
    }
}
