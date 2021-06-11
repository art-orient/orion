package com.art.orion.model.dao;

import com.art.orion.model.entity.User;

import java.util.List;

public interface UserDao {

    boolean createUser(User user);

    User getUser (String username);

    boolean updateUser (User user);

    int countUsers();

    List<User> getUsers();

    boolean validateCredentials (String username, String password);
}

