package com.art.orion.controller.listener;

import com.art.orion.model.pool.ConnectionPool;
import com.art.orion.model.pool.ConnectionPoolException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class ContextListener implements ServletContextListener {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        try {
            ConnectionPool.INSTANCE.initPool();
        } catch (ConnectionPoolException e) {
            logger.log(Level.FATAL,"Connection pool initialization error", e);
            throw new RuntimeException("Connection pool initialization error", e);
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        try {
            ConnectionPool.INSTANCE.destroyPool();
        } catch (ConnectionPoolException e) {
            logger.log(Level.FATAL,"Connection pool destruction error", e);
            throw new RuntimeException("Connection pool destruction error", e);
        }
    }
}
