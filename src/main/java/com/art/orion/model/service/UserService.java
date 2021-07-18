package com.art.orion.model.service;

import com.art.orion.exception.ServiceException;
import com.art.orion.model.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    boolean checkIsUsernameBusy(String username, StringBuilder validationStatus) throws ServiceException;

    boolean isFirstUser() throws ServiceException;

    boolean registerUser(User user) throws ServiceException;

    boolean validateCredentials(String username, String password) throws ServiceException;

    Optional<User> findUserByUsername(String username) throws ServiceException;

    List<User> findUsers(int limit, int offset) throws ServiceException;

    int countUsers() throws ServiceException;

    boolean updateUser(User user) throws ServiceException;

    boolean deleteUser(String username) throws ServiceException;
}