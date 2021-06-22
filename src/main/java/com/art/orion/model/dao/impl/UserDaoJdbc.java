package com.art.orion.model.dao.impl;

import com.art.orion.model.dao.UserDao;
import com.art.orion.model.entity.User;
import com.art.orion.model.pool.ConnectionPool;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class UserDaoJdbc implements UserDao {
    private static final Logger logger = LogManager.getLogger();
    private static final UserDaoJdbc INSTANCE = new UserDaoJdbc();
    private static final String INSERT_USER = "INSERT into users value (?, ?, ?, ?, ?, ?, ?)";

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
            System.out.println(user.getPassword().length() + "--------");
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
    public User getUser(String username) {
        return null;
    }

    @Override
    public boolean updateUser(User user) {
        return false;
    }

    @Override
    public int countUsers() {
        return 0;
    }

    @Override
    public List<User> getUsers() {
        return null;
    }

    @Override
    public boolean validateCredentials(String username, String password) {
        return false;
    }
}
