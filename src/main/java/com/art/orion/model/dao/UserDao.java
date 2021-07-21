package com.art.orion.model.dao;

import com.art.orion.exception.OrionDatabaseException;
import com.art.orion.model.entity.User;

import java.util.List;
import java.util.Optional;

/**
 * The {@code UserDao} interface for working with database table users
 *
 * @author Aliaksandr Artsikhovich
 * @version 1.0
 */
public interface UserDao {

    /**
     * Saves an user
     *
     * @param user {@link User} the user
     * @return the boolean is the user saved
     * @throws OrionDatabaseException the OrionDatabaseException exception     *
     */
    boolean createUser(User user) throws OrionDatabaseException;

    /**
     * Сhecks if the username is busy or free
     *
     * @param username {@link String} the username
     * @return the boolean is the username busy
     * @throws OrionDatabaseException the OrionDatabaseException exception
     */
    boolean checkIsUsernameBusy(String username) throws OrionDatabaseException;

    /**
     * Validates user credentials and activity
     *
     * @param username {@link String} the username
     * @param password {@link String} the password
     * @return the boolean[] are credentials valid and is active user
     * @throws OrionDatabaseException the OrionDatabaseException exception
     */
    boolean[] validateCredentialsAndActivity (String username, String password) throws OrionDatabaseException;

    /**
     * Сounts the number of all users
     *
     * @return the number of all users
     * @throws OrionDatabaseException the OrionDatabaseException exception
     */
    int countUsers() throws OrionDatabaseException;

    /**
     * Finds the user by his username
     *
     * @param username {@link String} the username
     * @return {@link Optional} of {@link User} the optional of found user
     * @throws OrionDatabaseException the OrionDatabaseException exception
     */
    Optional<User> findUserByUsername(String username) throws OrionDatabaseException;

    /**
     * Changes user data (role & activity), blocks and unblocks the user
     *
     * @param user {@link User} the user
     * @return the boolean is the user updated
     * @throws OrionDatabaseException the OrionDatabaseException exception
     */
    boolean updateUser(User user) throws OrionDatabaseException;

    /**
     * Finds all users
     *
     * @param limit number of users per page
     * @param offset index of the first user on the page
     * @return {@link List} of {@link User} the list of found users
     * @throws OrionDatabaseException the OrionDatabaseException exception
     */
    List<User> findUsers(int limit, int offset) throws OrionDatabaseException;

    /**
     * Removes the user by his username
     *
     * @param username {@link String} the username
     * @return the boolean is the user deleted
     * @throws OrionDatabaseException the OrionDatabaseException exception
     */
    boolean deleteUser(String username) throws OrionDatabaseException;
}

