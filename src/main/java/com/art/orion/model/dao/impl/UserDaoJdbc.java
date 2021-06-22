package com.art.orion.model.dao.impl;

import com.art.orion.model.dao.UserDao;
import com.art.orion.model.entity.User;

import java.util.List;

public class UserDaoJdbc implements UserDao {
    private static final UserDaoJdbc INSTANCE = new UserDaoJdbc();


    private UserDaoJdbc() {
    }

    public static UserDaoJdbc getInstance() {
        return INSTANCE;
    }

    @Override
    public boolean createUser(User user) {
        return false;
    }

    @Override
    public User getUser(String username) {
        return null;
    }

    @Override
    public boolean updateUser(User user) {
        return false;
    }

    @Override
    public int countUsers() {
        return 0;
    }

    @Override
    public List<User> getUsers() {
        return null;
    }

    @Override
    public boolean validateCredentials(String username, String password) {
        return false;
    }
}