package com.alex.crudapponboot.controllers;

import com.alex.crudapponboot.models.User;
import com.alex.crudapponboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/users")
public class UsersController {
    private final UserService userService;
    @Autowired
    public UsersController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping
    public String getUserById(@AuthenticationPrincipal User user, Model model) {
        model.addAttribute("user", user);
        return "users/show";
    }
//
//
//    @DeleteMapping("/{id}")
//    public String removeUserWhereId(@PathVariable("id") Long id) {
//        userService.removeUserById(id);
//        return "redirect:/show";
//    }


}
