package com.art.orion.model.pool;

import com.art.orion.util.ConfigManager;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public enum ConnectionPool {

    INSTANCE;

    private final Logger logger = LogManager.getLogger();
    private static final int POOL_SIZE = 8;
    private final BlockingQueue<ProxyConnection> freeConnections;
    private final Queue<ProxyConnection> givenConnections;

    ConnectionPool() {
        freeConnections = new LinkedBlockingQueue<>(POOL_SIZE);
        givenConnections = new ArrayDeque<>();
        String driverName = ConfigManager.getProperty("db.driver");
        String url = ConfigManager.getProperty("db.url");
        String username = ConfigManager.getProperty("db.username");
        String password = ConfigManager.getProperty("db.password");
        try {
            Class.forName(driverName);
            logger.log(Level.DEBUG, "MySQL driver loaded");
            for (int i = 0; i < POOL_SIZE; i++) {
                Connection connection = DriverManager.getConnection(url, username, password);
                freeConnections.offer(new ProxyConnection(connection));
            }
            logger.log(Level.INFO, "Database connection pool created");
        } catch (ClassNotFoundException | SQLException e) {
            logger.log(Level.FATAL,e.getMessage(), e);
            throw new RuntimeException(e); // user exceptions
        }
    }

    public Connection getConnection() {
        ProxyConnection connection = null;
        try {
            connection = freeConnections.take();
            givenConnections.offer(connection);
        } catch (InterruptedException e) {
            logger.log(Level.WARN,"Thread was interrupted", e);
        }
        return connection;
    }

    public void releaseConnection(Connection connection) {
        if (connection instanceof ProxyConnection) {
            givenConnections.remove(connection);
            freeConnections.offer((ProxyConnection) connection);
        } else {
            logger.log(Level.WARN,"Connection is not instance of ProxyConnection");
        }
    }

    public void destroyPool() {
        for (int i = 0; i < POOL_SIZE; i++) {
            try {
                freeConnections.take().reallyClose();
                logger.log(Level.INFO, "Connection is closed");
            } catch (SQLException | InterruptedException e) {
                logger.log(Level.ERROR, e.getMessage(), e);
            }
        }
        deregisterDriver();
    }

    private void deregisterDriver() {
        DriverManager.getDrivers().asIterator().forEachRemaining(driver -> {
            try {
                DriverManager.deregisterDriver(driver);
                logger.log(Level.DEBUG, "MySQL driver deregistered");
            } catch (SQLException e) {
                logger.log(Level.ERROR, e.getMessage(), e);
            }
        });
    }
}

