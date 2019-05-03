package com.ahmetov.conference.controller;

import com.ahmetov.conference.constant.Role;
import com.ahmetov.conference.entities.User;
import com.ahmetov.conference.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("/admin")
@PreAuthorize("hasAuthority('ADMIN')")
public class AdminController {
    @Autowired
    private UserService userService;

    @GetMapping()
    public String usersPage(Model model){
        List<User> users = userService.findAll();
        model.addAttribute("users",users);
        User user = new User();
        user.setRoles(Collections.singleton(Role.USER));

        model.addAttribute("user", user);

        return "users";
    }

    @PostMapping(value = "deleteUser")
    public String deletePresentation(@RequestParam String id){
        userService.deleteUserById(id);
        return "redirect:/admin";
    }

    @PostMapping(value = "addUser")
    public String addPresentation(@ModelAttribute User user){
        userService.save(user);
        return "redirect:/admin";
    }
}
