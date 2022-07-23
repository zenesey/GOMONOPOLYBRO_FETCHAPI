package com.alex.crudapponboot.controllers;

import com.alex.crudapponboot.models.User;
import com.alex.crudapponboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/users")
public class UsersController {
    @Autowired
    private UserService userService;

    @GetMapping()
    public String getAllUsers(Model model) {
    model.addAttribute("users",userService.getAllUsers());
    return "users/users";
    }

    @GetMapping("/{id}")
    public String getUserById(@PathVariable ("id") Long id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        return "users/show";
    }
    @GetMapping("/new")
    public String newUser(Model model) {
        model.addAttribute("user",new User());

        return "users/new";
    }
    @PostMapping
    public String create(@ModelAttribute("user") User user) {
        userService.saveUser(user);
        return "redirect:/users";

    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") long id) {
        model.addAttribute("user", userService.getUserById(id));
        return "users/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("user") User user, @PathVariable("id") long id) {
        userService.updateUserById(id, user);
        return "redirect:/users";
    }
    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable("id") long id) {
    userService.removeUserById(id);

        return "redirect:/users";
    }
//
//
//    @DeleteMapping("/{id}")
//    public String removeUserWhereId(@PathVariable("id") Long id) {
//        userService.removeUserById(id);
//        return "redirect:/show";
//    }


}
