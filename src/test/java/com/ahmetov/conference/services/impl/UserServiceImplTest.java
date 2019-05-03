package com.ahmetov.conference.services.impl;

import com.ahmetov.conference.constant.Role;
import com.ahmetov.conference.entities.User;
import com.ahmetov.conference.services.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureTestDatabase
public class UserServiceImplTest {
    @Autowired
    UserService userService;

    @Test
    public void save() {
        User user = new User();
        user.setId(1L);
        Assert.assertTrue(userService.save(user));
        user.setRoles(Collections.singleton(Role.USER));
        Assert.assertFalse(userService.save(user));
    }
}