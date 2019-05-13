package com.ahmetov.conference.controller;

import com.ahmetov.conference.constant.Role;
import com.ahmetov.conference.dto.UserDto;
import com.ahmetov.conference.entities.User;
import com.ahmetov.conference.services.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collections;

/**
 * controller for registration page
 */
@Controller
public class RegistrationController {
    private UserService userService;

    @Autowired
    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/registration")
    public String registration(Model model) {
        UserDto user = new UserDto();
        model.addAttribute("user", user);
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(@ModelAttribute UserDto userDto, Model model) {
        User user = new User();
        BeanUtils.copyProperties(userDto,user);
        User userFromDb = userService.findByLogin(user.getUsername());
        if (userFromDb != null) {
            return "registration";
        }
        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        userService.save(user);
        return "redirect:/login";
    }
}
