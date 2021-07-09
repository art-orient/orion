package com.art.orion.model.service;

import com.art.orion.model.dao.OrionDatabaseException;
import com.art.orion.model.dao.UserDao;
import com.art.orion.model.dao.impl.UserDaoJdbc;
import com.art.orion.model.entity.User;

import java.sql.SQLException;

public class UserService {
    private static final UserDao USER_DAO = UserDaoJdbc.getInstance();

    private UserService() {
    }

    public static boolean checkIsUsernameBusy(String username) throws ServiceException {
        try {
            return USER_DAO.checkIsUsernameBusy(username);
        } catch (OrionDatabaseException e) {
            throw new ServiceException("Database access error occurred while validating username", e);
        }
    }

    public static boolean isFirstUser() throws ServiceException {
        try {
            return USER_DAO.countUsers() == 0;
        } catch (OrionDatabaseException e) {
            throw new ServiceException("An error occurred while counting users in the database", e);
        }
    }

    public static boolean registerUser(User user) throws ServiceException {
        try {
            return USER_DAO.createUser(user);
        } catch (SQLException | OrionDatabaseException e) {
            throw new ServiceException("user registration error", e);
        }
    }

    public static boolean validateCredentials(String username, String password) throws ServiceException {
        try {
            return USER_DAO.validateCredentials(username, password);
        } catch (OrionDatabaseException e) {
            throw new ServiceException("Database access error occurred while validating credentials", e);
        }
    }

    public static User getUser(String username) throws ServiceException {
        try {
            return USER_DAO.getUser(username);
        } catch (OrionDatabaseException e) {
            throw new ServiceException("An error occurred while getting the user from the database", e);
        }
    }
}
