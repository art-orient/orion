package com.art.orion.model.service.impl;

import com.art.orion.exception.OrionDatabaseException;
import com.art.orion.model.dao.UserDao;
import com.art.orion.model.dao.impl.UserDaoJdbc;
import com.art.orion.model.entity.User;
import com.art.orion.exception.ServiceException;
import com.art.orion.model.service.UserService;
import com.art.orion.util.ErrorMessageManager;

import java.sql.SQLException;
import java.util.Optional;

public class UserServiceImpl implements UserService {
    private static final UserDao USER_DAO = UserDaoJdbc.getInstance();

    @Override
    public boolean checkIsUsernameBusy(String username, StringBuilder validationStatus) throws ServiceException {
        boolean isUsernameBusy;
        try {
            isUsernameBusy = USER_DAO.checkIsUsernameBusy(username);
        } catch (OrionDatabaseException e) {
            validationStatus.append(e.getMessage());
            throw new ServiceException("Database access error occurred while validating username", e);
        }
        if (isUsernameBusy) {
            validationStatus.append(ErrorMessageManager.getMessage("msg.nameExists")).append("\n");
        }
        return isUsernameBusy;
    }

    public boolean isFirstUser() throws ServiceException {
        try {
            return USER_DAO.countUsers() == 0;
        } catch (OrionDatabaseException e) {
            throw new ServiceException("An error occurred while counting users in the database", e);
        }
    }

    public boolean registerUser(User user) throws ServiceException {
        try {
            return USER_DAO.createUser(user);
        } catch (SQLException | OrionDatabaseException e) {
            throw new ServiceException("user registration error", e);
        }
    }

    public boolean validateCredentials(String username, String password) throws ServiceException {
        try {
            return USER_DAO.validateCredentials(username, password);
        } catch (OrionDatabaseException e) {
            throw new ServiceException("Database access error occurred while validating credentials", e);
        }
    }

    public Optional<User> findUserByUsername(String username) throws ServiceException {
        try {
            return USER_DAO.findUserByUsername(username);
        } catch (OrionDatabaseException e) {
            throw new ServiceException("An error occurred while retrieving the user from the database", e);
        }
    }
}
