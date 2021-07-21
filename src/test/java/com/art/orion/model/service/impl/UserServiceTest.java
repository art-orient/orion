package com.art.orion.model.service.impl;

import com.art.orion.exception.OrionDatabaseException;
import com.art.orion.exception.ServiceException;
import com.art.orion.model.dao.impl.UserDaoJdbc;
import com.art.orion.model.entity.Role;
import com.art.orion.model.entity.User;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

//@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    @Mock
    private UserDaoJdbc USER_DAO;
    private AutoCloseable autoCloseable;
    private User user;

    @InjectMocks
    private UserServiceImpl userService;

    @BeforeClass
    public void setUp() {
        user = new User("alexei", "alexei", "Алексей", "Алексеев",
                "alekseev@al.com", Role.MANAGER, true);
    }

    @BeforeMethod
    public void init() {
        autoCloseable = MockitoAnnotations.openMocks(this);
    }

    @AfterMethod
    public void tierDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    public void isUsernameBusyPositiveTest() throws OrionDatabaseException, ServiceException {
        when(USER_DAO.checkIsUsernameBusy(any(String.class))).thenReturn(true);
        Assert.assertTrue(userService.checkIsUsernameBusy("admin", new StringBuilder("message")));
    }

    @Test
    public void isUsernameBusyNegativeTest() throws OrionDatabaseException, ServiceException {
        when(USER_DAO.checkIsUsernameBusy(any(String.class))).thenReturn(false);
        Assert.assertFalse(userService.checkIsUsernameBusy("admin", new StringBuilder("message")));
    }

    @Test
    public void isFirstUserPositiveTest() throws OrionDatabaseException, ServiceException {
        when(USER_DAO.countUsers()).thenReturn(0);
        Assert.assertTrue(userService.isFirstUser());
    }

    @Test
    public void isFirstUserNegativeTest() throws OrionDatabaseException, ServiceException {
        when(USER_DAO.countUsers()).thenReturn(5);
        Assert.assertFalse(userService.isFirstUser());
    }
}
