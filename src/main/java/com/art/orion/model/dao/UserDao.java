package com.art.orion.model.dao;

import com.art.orion.exception.OrionDatabaseException;
import com.art.orion.model.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserDao {

    boolean createUser(User user) throws OrionDatabaseException;

    boolean checkIsUsernameBusy(String username) throws OrionDatabaseException;

    boolean validateCredentials (String username, String password) throws OrionDatabaseException;

    int countUsers() throws OrionDatabaseException;

    Optional<User> findUserByUsername(String username) throws OrionDatabaseException;

    List<User> getUsers() throws OrionDatabaseException;

    boolean updateUser(User user) throws OrionDatabaseException;
}

