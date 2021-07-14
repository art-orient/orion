package com.art.orion.model.service;

import com.art.orion.exception.ServiceException;
import com.art.orion.model.entity.User;

import java.util.Optional;

public interface UserService {

    boolean checkIsUsernameBusy(String username, StringBuilder validationStatus) throws ServiceException;

    boolean isFirstUser() throws ServiceException;

    boolean registerUser(User user) throws ServiceException;

    boolean validateCredentials(String username, String password) throws ServiceException;

    Optional<User> findUserByUsername(String username) throws ServiceException;
}