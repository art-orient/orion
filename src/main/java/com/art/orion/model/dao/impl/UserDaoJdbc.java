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
    private static final String GET_USER_BY_CREDENTIALS = "SELECT username FROM users" +
            " WHERE username = ? AND password = ?";

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

    //    not currently used
    @Override
    public List<User> getUsers() {
        throw new UnsupportedOperationException("operation not supported");
    }

    //    not currently used
    @Override
    public boolean updateUser(User user) {
        throw new UnsupportedOperationException("operation not supported");
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
