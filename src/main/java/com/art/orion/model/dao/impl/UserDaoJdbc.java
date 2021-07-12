package com.art.orion.model.dao.impl;

import com.art.orion.model.dao.OrionDatabaseException;
import com.art.orion.model.dao.UserDao;
import com.art.orion.model.entity.Role;
import com.art.orion.model.entity.User;
import com.art.orion.model.pool.ConnectionPool;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import static com.art.orion.util.Constant.DATABASE_EXCEPTION;
import static com.art.orion.util.Constant.USERNAME;
import static com.art.orion.util.Constant.PASSWORD;
import static com.art.orion.util.Constant.FIRSTNAME;
import static com.art.orion.util.Constant.LASTNAME;
import static com.art.orion.util.Constant.EMAIL;
import static com.art.orion.util.Constant.ROLE;
import static com.art.orion.util.Constant.ACTIVE;

public class UserDaoJdbc implements UserDao {
    private static final Logger logger = LogManager.getLogger();
    private static final UserDaoJdbc INSTANCE = new UserDaoJdbc();
    private static final String INSERT_USER = "INSERT INTO users VALUE (?, ?, ?, ?, ?, ?, ?)";
    private static final String COUNT_USERS = "SELECT COUNT(*) FROM users";
    private static final String GET_USERNAME = "SELECT username FROM users WHERE username = ?";
    private static final String GET_USER_BY_USERNAME = "SELECT username, password, firstname, lastname, email," +
            " role, active FROM users WHERE username = ?";
    private static final String GET_USER_BY_CREDENTIALS = "SELECT username FROM users" +
            " WHERE username = ? AND password = ?";
    private static final int USERNAME_INDEX = 1;
    private static final int PASSWORD_INDEX = 2;
    private static final int FIRSTNAME_INDEX = 3;
    private static final int LASTNAME_INDEX = 4;
    private static final int EMAIL_INDEX = 5;
    private static final int ROLE_INDEX = 6;
    private static final int ACTIVE_INDEX = 7;

    private UserDaoJdbc() {
    }

    public static UserDaoJdbc getInstance() {
        return INSTANCE;
    }

    @Override
    public boolean createUser(User user) throws SQLException, OrionDatabaseException {
        boolean isAddUser;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = ConnectionPool.INSTANCE.getConnection();
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(INSERT_USER);
            preparedStatement.setString(USERNAME_INDEX, user.getUsername());
            preparedStatement.setString(PASSWORD_INDEX, user.getPassword());
            preparedStatement.setString(FIRSTNAME_INDEX, user.getFirstName());
            preparedStatement.setString(LASTNAME_INDEX, user.getLastName());
            preparedStatement.setString(EMAIL_INDEX, user.getEmail());
            preparedStatement.setInt(ROLE_INDEX, user.getRole().ordinal());
            preparedStatement.setBoolean(ACTIVE_INDEX, user.isActive());
            isAddUser = (preparedStatement.executeUpdate() == 1);
            connection.commit();
            connection.setAutoCommit(true);
            logger.log(Level.INFO, () -> "The user is saved in the database");
        } catch (SQLException e) {
            connection.rollback();
            throw new OrionDatabaseException(DATABASE_EXCEPTION, e);
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        return isAddUser;
    }

    @Override
    public boolean checkIsUsernameBusy(String username) throws OrionDatabaseException {
        boolean isUsernameBusy = false;
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_USERNAME)) {
            preparedStatement.setString(1, username);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    isUsernameBusy = true;
                }
            }
            logger.log(Level.DEBUG, () -> String.format("The user %s is already present in the database", username));
        } catch (SQLException e) {
            throw new OrionDatabaseException(DATABASE_EXCEPTION, e);
        }
        return isUsernameBusy;
    }

    @Override
    public User getUser(String username) throws OrionDatabaseException {
        User user = new User();
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_USER_BY_USERNAME)) {
            preparedStatement.setString(1, username);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    createUserFromDatabase(resultSet, user);
                }
            }
            logger.log(Level.DEBUG, () -> String.format("The user %s got from the database", username));
        } catch (SQLException e) {
            throw new OrionDatabaseException(DATABASE_EXCEPTION, e);
        }
        return user;
    }

    private void createUserFromDatabase(ResultSet resultSet, User user) throws SQLException {
        user.setUsername(resultSet.getString(USERNAME));
        user.setPassword(resultSet.getString(PASSWORD));
        user.setFirstName(resultSet.getString(FIRSTNAME));
        user.setLastName(resultSet.getString(LASTNAME));
        user.setEmail(resultSet.getString(EMAIL));
        user.setRole(Role.values()[resultSet.getInt(ROLE)]);
        user.setActive(resultSet.getBoolean(ACTIVE));
    }

    @Override
    public boolean updateUser(User user) {
        throw new UnsupportedOperationException("operation not supported");
    }

    @Override
    public int countUsers() throws OrionDatabaseException {
        int numberUsers = 0;
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(COUNT_USERS)) {
            if (resultSet.next()) {
                numberUsers = resultSet.getInt(1);
            }
            logger.log(Level.DEBUG, () -> "The number of users retrieved from the database");
        } catch (SQLException e) {
            throw new OrionDatabaseException(DATABASE_EXCEPTION, e);
        }
        return numberUsers;
    }

    @Override
    public List<User> getUsers() {
        throw new UnsupportedOperationException("operation not supported");
    }

    @Override
    public boolean validateCredentials(String username, String password) throws OrionDatabaseException {
        boolean isValid = false;
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_USER_BY_CREDENTIALS)) {
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    isValid = true;
                }
            }
            logger.log(Level.DEBUG, () -> "Username and password are valid");
        } catch (SQLException e) {
            throw new OrionDatabaseException(DATABASE_EXCEPTION, e);
        }
        return isValid;
    }
}
