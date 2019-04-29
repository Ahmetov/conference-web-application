package com.ahmetov.conference.services.impl;

import com.ahmetov.conference.entities.User;
import com.ahmetov.conference.repository.UserRepository;
import com.ahmetov.conference.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;

public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    public User findUserById(Long id){
        return userRepository.findUserById(id);
    }

    public void deleteUserById(Long id){
        userRepository.deleteUserById(id);
    }

    public Collection<User> findAll(){
        return userRepository.findAll();
    }
}
