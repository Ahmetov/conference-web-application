package com.ahmetov.conference.controller;

import com.ahmetov.conference.entities.User;
import com.ahmetov.conference.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/admin/users")
@PreAuthorize("hasAuthority('ADMIN')")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping()
    public String usersPage(Model model){
        List<User> users = userService.findAll();
        model.addAttribute("users",users);

        return "users";
    }

    @PostMapping(value = "deletePresentation")
    public String deletePresentation(@RequestParam String id){
        userService.deleteUserById(id);
        return "redirect:/admin/users";
    }
}
