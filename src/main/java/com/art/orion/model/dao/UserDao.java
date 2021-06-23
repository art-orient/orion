package com.art.orion.model.dao;

import com.art.orion.model.entity.User;

import java.util.List;

public interface UserDao {

    boolean createUser(User user);

    boolean checkIsUsernameBusy(String username);

    int countUsers();

    User getUser (String username);

    boolean updateUser (User user);

    List<User> getUsers();

    boolean validateCredentials (String username, String password);
}

