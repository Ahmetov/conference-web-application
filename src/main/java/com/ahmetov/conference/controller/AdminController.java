package com.ahmetov.conference.controller;

import com.ahmetov.conference.constant.Role;
import com.ahmetov.conference.dto.UserDto;
import com.ahmetov.conference.entities.User;
import com.ahmetov.conference.services.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * Controller for admin panel (allowed for admin)
 */
@Controller
@RequestMapping("/admin")
@PreAuthorize("hasAuthority('ADMIN')")
public class AdminController {
    private UserService userService;

    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public String usersPage(Model model) {
        List<User> users = userService.findAll();
        model.addAttribute("users", users);
        UserDto user = new UserDto();
        user.setRoles(Collections.singleton(Role.USER));
        model.addAttribute("user", user);
        return "users";
    }

    @PostMapping(value = "deleteUser")
    public String deleteUser(@RequestParam String id) {
        userService.deleteUserById(id);
        return "redirect:/admin";
    }

    @PostMapping(value = "addUser")
    public String addUser(@ModelAttribute UserDto userDto) {
        User user = new User();
        BeanUtils.copyProperties(userDto,user);
        userService.save(user);
        return "redirect:/admin";
    }

    @PostMapping(value = "setLectorUser")
    public String setLector(@RequestParam String id) {
        User user = userService.findUserById(id);
        Set<Role> roleSet = user.getRoles();
        if (!roleSet.contains(Role.PRESENTER)) {
            roleSet = user.getRoles();
            roleSet.add(Role.PRESENTER);
            user.setRoles(roleSet);
            userService.save(user);
        }

        return "redirect:/admin";
    }
}
