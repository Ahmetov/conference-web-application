package com.ahmetov.conference.services;

import com.ahmetov.conference.entities.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public interface UserService extends UserDetailsService{
    User findUserById(String id);
    void deleteUserById(String id);
    User findByLogin(String login);
    boolean save(User user);
}
