package com.art.orion.model.dao.impl;

import com.art.orion.exception.OrionDatabaseException;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.art.orion.model.dao.column.UsersColumn.ACTIVE;
import static com.art.orion.model.dao.column.UsersColumn.ACTIVE_INDEX;
import static com.art.orion.model.dao.column.UsersColumn.EMAIL;
import static com.art.orion.model.dao.column.UsersColumn.EMAIL_INDEX;
import static com.art.orion.model.dao.column.UsersColumn.FIRSTNAME;
import static com.art.orion.model.dao.column.UsersColumn.FIRSTNAME_INDEX;
import static com.art.orion.model.dao.column.UsersColumn.LASTNAME;
import static com.art.orion.model.dao.column.UsersColumn.LASTNAME_INDEX;
import static com.art.orion.model.dao.column.UsersColumn.PASSWORD;
import static com.art.orion.model.dao.column.UsersColumn.PASSWORD_INDEX;
import static com.art.orion.model.dao.column.UsersColumn.ROLE;
import static com.art.orion.model.dao.column.UsersColumn.ROLE_INDEX;
import static com.art.orion.model.dao.column.UsersColumn.USERNAME;
import static com.art.orion.model.dao.column.UsersColumn.USERNAME_INDEX;
import static com.art.orion.util.Constant.DATABASE_EXCEPTION;

public class UserDaoJdbc implements UserDao {
    private static final Logger logger = LogManager.getLogger();
    private static final UserDaoJdbc INSTANCE = new UserDaoJdbc();
    private static final String INSERT_USER = "INSERT INTO users VALUE (?, ?, ?, ?, ?, ?, ?)";
    private static final String COUNT_USERS = "SELECT COUNT(*) FROM users";
    private static final String GET_USERNAME = "SELECT username FROM users WHERE username = ?";
    private static final String GET_USER_BY_USERNAME = "SELECT username, password, firstname, lastname, email," +
            " role, active FROM users WHERE username = ?";
    private static final String GET_USER_BY_CREDENTIALS = "SELECT username, active FROM users" +
            " WHERE username = ? AND password = ?";
    private static final String SELECT_ALL_USERS = "SELECT username, password, firstname, lastname, email, " +
            "role, active FROM users LIMIT ? OFFSET ?";
    private static final String UPDATE_USER = "UPDATE users SET password = ?, firstname = ?, lastname = ?, " +
            "email = ?, role = ?, active = ? WHERE username = ?";
    private static final int UPDATE_USERNAME_INDEX = 7;
    private static final String DELETE_USER = "DELETE FROM users WHERE username = ?";

    private UserDaoJdbc() {
    }

    public static UserDaoJdbc getInstance() {
        return INSTANCE;
    }

    @Override
    public boolean createUser(User user) throws OrionDatabaseException {
        boolean isAddUser;
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USER)) {
            preparedStatement.setString(USERNAME_INDEX, user.getUsername());
            preparedStatement.setString(PASSWORD_INDEX, user.getPassword());
            preparedStatement.setString(FIRSTNAME_INDEX, user.getFirstName());
            preparedStatement.setString(LASTNAME_INDEX, user.getLastName());
            preparedStatement.setString(EMAIL_INDEX, user.getEmail());
            preparedStatement.setInt(ROLE_INDEX, user.getRole().ordinal());
            preparedStatement.setBoolean(ACTIVE_INDEX, user.isActive());
            isAddUser = (preparedStatement.executeUpdate() == 1);
            logger.log(Level.INFO, () -> "The user is saved in the database");
        } catch (SQLException e) {
            throw new OrionDatabaseException(DATABASE_EXCEPTION, e);
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
    public boolean[] validateCredentialsAndActivity (String username, String password) throws OrionDatabaseException {
        boolean[] result = new boolean[2];
        boolean isValid = false;
        boolean isActive = false;
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_USER_BY_CREDENTIALS)) {
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    isValid = true;
                    isActive = resultSet.getBoolean(ACTIVE);
                }
            }
            result[0] = isValid;
            result[1] = isActive;
            logger.log(Level.DEBUG, () -> "Username and password are valid");
        } catch (SQLException e) {
            throw new OrionDatabaseException(DATABASE_EXCEPTION, e);
        }
        return result;
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
    public Optional<User> findUserByUsername(String username) throws OrionDatabaseException {
        Optional<User> optionalUser = Optional.empty();
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_USER_BY_USERNAME)) {
            preparedStatement.setString(1, username);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    User user = buildUser(resultSet);
                    optionalUser = Optional.of(user);
                }
            }
            logger.log(Level.DEBUG, () -> String.format("The user %s got from the database", username));
        } catch (SQLException e) {
            throw new OrionDatabaseException(DATABASE_EXCEPTION, e);
        }
        return optionalUser;
    }

    @Override
    public List<User> findUsers(int limit, int offset) throws OrionDatabaseException {
        List<User> users = new ArrayList<>();
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_USERS)) {
            preparedStatement.setInt(1, limit);
            preparedStatement.setInt(2, offset);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    User user = buildUser(resultSet);
                    users.add(user);
                }
            }
            logger.log(Level.DEBUG, () -> "Users got from the database");
        } catch (SQLException e) {
            throw new OrionDatabaseException(DATABASE_EXCEPTION, e);
        }
        return users;
    }

    @Override
    public boolean updateUser(User user) throws OrionDatabaseException {
        boolean isUserUpdated;
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USER)) {
            preparedStatement.setString(PASSWORD_INDEX -1, user.getPassword());
            preparedStatement.setString(FIRSTNAME_INDEX - 1, user.getFirstName());
            preparedStatement.setString(LASTNAME_INDEX - 1, user.getLastName());
            preparedStatement.setString(EMAIL_INDEX - 1, user.getEmail());
            preparedStatement.setInt(ROLE_INDEX - 1, user.getRole().ordinal());
            preparedStatement.setBoolean(ACTIVE_INDEX - 1, user.isActive());
            preparedStatement.setString(UPDATE_USERNAME_INDEX, user.getUsername());
            isUserUpdated = (preparedStatement.executeUpdate() == 1);
            logger.log(Level.INFO, "The user {} is updated", user.getUsername());
        } catch (SQLException e) {
            throw new OrionDatabaseException(DATABASE_EXCEPTION, e);
        }
        return isUserUpdated;
    }

    @Override
    public boolean deleteUser(String username) throws OrionDatabaseException {
        boolean isUserDeleted;
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_USER)) {
            preparedStatement.setString(USERNAME_INDEX, username);
            isUserDeleted = (preparedStatement.executeUpdate() == 1);
            logger.log(Level.INFO, "The user {} is deleted", username);
        } catch (SQLException e) {
            throw new OrionDatabaseException(DATABASE_EXCEPTION, e);
        }
        return isUserDeleted;
    }

    private User buildUser(ResultSet resultSet) throws SQLException {
        String username = resultSet.getString(USERNAME);
        String password = resultSet.getString(PASSWORD);
        String firstname = resultSet.getString(FIRSTNAME);
        String lastname = resultSet.getString(LASTNAME);
        String email = resultSet.getString(EMAIL);
        Role role = Role.values()[resultSet.getInt(ROLE)];
        boolean isActive = resultSet.getBoolean(ACTIVE);
        return new User(username, password, firstname, lastname, email, role, isActive);
    }
}
