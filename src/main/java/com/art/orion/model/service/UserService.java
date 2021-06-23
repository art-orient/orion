package com.art.orion.model.service;

import com.art.orion.model.dao.UserDao;
import com.art.orion.model.dao.impl.UserDaoJdbc;
import com.art.orion.model.entity.User;

public class UserService {
    private static final UserDao USER_DAO = UserDaoJdbc.getInstance();

    private UserService() {
    }

    public static boolean checkIsUsernameBusy(String username) {
        return USER_DAO.checkIsUsernameBusy(username);
    }

    public static boolean isFirstUser() {
        return USER_DAO.countUsers() == 0;
    }

    public static boolean registerUser(User user) {
        return USER_DAO.createUser(user);
    }
}
