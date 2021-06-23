package com.art.orion.model.dao.impl;

import com.art.orion.model.dao.UserDao;
import com.art.orion.model.entity.Role;
import com.art.orion.model.entity.User;
import com.art.orion.model.pool.ConnectionPool;
import com.art.orion.util.Constant;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.List;

import static com.art.orion.util.Constant.*;

public class UserDaoJdbc implements UserDao {
    private static final Logger logger = LogManager.getLogger();
    private static final UserDaoJdbc INSTANCE = new UserDaoJdbc();
    private static final String INSERT_USER = "INSERT INTO users VALUE (?, ?, ?, ?, ?, ?, ?)";
    private static final String COUNT_USERS = "SELECT COUNT(*) FROM users";
    private static final String GET_USER = "SELECT * FROM users WHERE username = ?";
    private static final String GET_USER_BY_CREDENTIALS = "SELECT * FROM users WHERE username = ? AND password = ?";

    private UserDaoJdbc() {
    }

    public static UserDaoJdbc getInstance() {
        return INSTANCE;
    }

    @Override
    public boolean createUser(User user) {
        boolean isAddUser = false;
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USER)) {
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getFirstName());
            preparedStatement.setString(4, user.getLastName());
            preparedStatement.setString(5, user.getEmail());
            preparedStatement.setInt(6, user.getRole().ordinal());
            preparedStatement.setBoolean(7, user.isActive());
            isAddUser = (preparedStatement.executeUpdate() == 1);
        } catch (SQLException e) {
            logger.log(Level.ERROR, e.getMessage(), e);
        }
        return isAddUser;
    }

    @Override
    public boolean checkIsUsernameBusy(String username) {
        boolean isUsernameBusy = false;
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_USER)) {
            preparedStatement.setString(1, username);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    isUsernameBusy = true;
                }
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, e.getMessage(), e);
        }
        return isUsernameBusy;
    }

    @Override
    public User getUser(String username) {
        User user = null;
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_USER)) {
            preparedStatement.setString(1, username);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    user = createUserFromDatabase(resultSet);
                }
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, e.getMessage(), e);
        }
        return user;
    }

    private User createUserFromDatabase(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setUsername(resultSet.getString(USERNAME));
        user.setPassword(resultSet.getString(PASSWORD));
        user.setFirstName(resultSet.getString(FIRSTNAME));
        user.setLastName(resultSet.getString(LASTNAME));
        user.setEmail(resultSet.getString(EMAIL));
        user.setRole(Role.values()[resultSet.getInt(ROLE)]);
        user.setActive(resultSet.getBoolean(ACTIVE));
        return user;
    }

    @Override
    public boolean updateUser(User user) {
        return false;
    }

    @Override
    public int countUsers() {
        int numberUsers = 0;
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(COUNT_USERS)) {
            if (resultSet.next()) {
                numberUsers = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, e.getMessage(), e);
        }
        return numberUsers;
    }

    @Override
    public List<User> getUsers() {

        return null;
    }

    @Override
    public boolean validateCredentials(String username, String password) {
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
        } catch (SQLException e) {
            logger.log(Level.ERROR, e.getMessage(), e);
        }
        return isValid;
    }
}
