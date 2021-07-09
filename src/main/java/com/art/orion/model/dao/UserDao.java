package com.art.orion.model.dao;

import com.art.orion.model.entity.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDao {

    boolean createUser(User user) throws SQLException, OrionDatabaseException;

    boolean checkIsUsernameBusy(String username) throws OrionDatabaseException;

    int countUsers() throws OrionDatabaseException;

    User getUser (String username) throws OrionDatabaseException;

    boolean updateUser (User user);

    List<User> getUsers();

    boolean validateCredentials (String username, String password) throws OrionDatabaseException;
}

