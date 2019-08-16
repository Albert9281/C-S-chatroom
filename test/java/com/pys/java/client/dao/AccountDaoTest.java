package com.pys.java.client.dao;

import org.junit.Test;
import com.pys.java.client.entity.User;
import org.junit.Assert;


public class AccountDaoTest {

    private AccountDao accountDao = new AccountDao();

    @Test
    public void userReg() {
        User user = new User();
        user.setUserName("test");
        user.setPassword("123");
        user.setBrief("帅");
        boolean flag = accountDao.userReg(user);
        Assert.assertTrue(flag);
    }

    @Test
    public void userLogin() {
        String userName = "test";
        String password = "123";
        User user = accountDao.userLogin(userName,password);
        System.out.println(user);
        Assert.assertNotNull(user);
    }
}