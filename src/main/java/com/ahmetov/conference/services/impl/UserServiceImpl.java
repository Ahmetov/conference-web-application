package com.ahmetov.conference.services.impl;

import com.ahmetov.conference.constant.Role;
import com.ahmetov.conference.entities.User;
import com.ahmetov.conference.repository.UserRepository;
import com.ahmetov.conference.services.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;


@Service
public class UserServiceImpl implements UserService {
    private static final Logger logger = Logger.getLogger("loggs");

    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findUserById(String id) {
        Long parsedId = Long.parseLong(id);
        return userRepository.findById(parsedId).orElse(null);
    }

    @Override
    public void deleteUserById(String id) {
        try {
            Long parsedId = Long.parseLong(id);
            userRepository.deleteUserById(parsedId);
        } catch (NumberFormatException ex) {
            logger.error(ex);
        }
    }

    @Override
    public boolean save(User user) {
        if (!user.getRoles().contains(Role.USER)) {
            user.setRoles(Collections.singleton(Role.USER));
            userRepository.save(user);
            return true;
        }
        userRepository.save(user);
        return false;
    }

    @Override
    public UserDetails loadUserByUsername(String username){
        BCryptPasswordEncoder encoder = passwordEncoder();
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        if (user.getUsername().equals("admin") && user.getPassword().equals("admin")) {
            return new org.springframework.security.core.userdetails.User(user.getUsername(), encoder.encode(user.getPassword()), getGrantedAdminAuthorities());
        }

        return new org.springframework.security.core.userdetails.User(user.getUsername(), encoder.encode(user.getPassword()), getGrantedAuthorities(user));
    }

    private Collection<GrantedAuthority> getGrantedAuthorities(User user) {
        Collection<GrantedAuthority> grantedAuthority = new ArrayList<>();
        grantedAuthority.add(new SimpleGrantedAuthority("USER"));
        if (user.getRoles().contains(Role.PRESENTER)){
            grantedAuthority.add(new SimpleGrantedAuthority("PRESENTER"));
        }

        return grantedAuthority;
    }

    private Collection<GrantedAuthority> getGrantedAdminAuthorities() {
        Collection<GrantedAuthority> grantedAuthority = new ArrayList<>();
        grantedAuthority.add(new SimpleGrantedAuthority("ADMIN"));
        grantedAuthority.add(new SimpleGrantedAuthority("PRESENTER"));
        return grantedAuthority;
    }

    @Override
    public User findByLogin(String login) {
        return userRepository.findByUsername(login);
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
