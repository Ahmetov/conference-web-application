package com.ahmetov.conference.services.impl;

import com.ahmetov.conference.constant.Role;
import com.ahmetov.conference.entities.User;
import com.ahmetov.conference.repository.UserRepository;
import com.ahmetov.conference.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

//    @Autowired
//    private BCryptPasswordEncoder passwordEncoder;
//


    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findUserById(String id) {
        return null;
    }

    @Override
    public void deleteUserById(String id) {
        try {
            Long parsedId = Long.parseLong(id);
            userRepository.deleteUserById(parsedId);
        } catch (NumberFormatException ex){

        }
    }

    @Override
    public boolean save(User user) {
        user.setRoles(Collections.singleton(Role.USER));
        userRepository.save(user);
        return true;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        BCryptPasswordEncoder encoder = passwordEncoder();


        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        if(user.getUsername().equals("admin") && user.getPassword().equals("admin")){
            return new org.springframework.security.core.userdetails.User(user.getUsername(), encoder.encode(user.getPassword()), getGrantedAdminAuthorities(user));
        }

        return new org.springframework.security.core.userdetails.User(user.getUsername(), encoder.encode(user.getPassword()), getGrantedAuthorities(user));
    }


    private Collection<GrantedAuthority> getGrantedAuthorities(User user){
        Collection<GrantedAuthority> grantedAuthority = new ArrayList<>();
        grantedAuthority.add(new SimpleGrantedAuthority("USER"));
        return grantedAuthority;
    }

    private Collection<GrantedAuthority> getGrantedAdminAuthorities(User user){
        Collection<GrantedAuthority> grantedAuthority = new ArrayList<>();
        grantedAuthority.add(new SimpleGrantedAuthority("ADMIN"));
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
