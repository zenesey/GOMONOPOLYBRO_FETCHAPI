package com.alex.crudapponboot.controllers;


import com.alex.crudapponboot.models.Role;
import com.alex.crudapponboot.models.User;
import com.alex.crudapponboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final UserService userService;
    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }

    // ПОЛУЧИТЬ ВСЕХ ЮЗЕРОВ
    @GetMapping()
    public String getAllUsers(Model model) {
        model.addAttribute("users",userService.getAllUsers());
        return "admin/users";
    }


    // ПОЛУЧИТЬ ЮЗЕРА ПО ID
    @GetMapping("/{id}")
    public String getUserById(@PathVariable("id") Long id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        return "admin/show";
    }
    // СОЗДАТЬ НОВОГО ЮЗЕРА
    @GetMapping("/new")
    public String newUser(Model model) {
        model.addAttribute("user",new User());
        model.addAttribute("allRoles", userService.getAllRoles());

        return "admin/new";
    }
    // СОХРАНИТЬ НОВОГО ЮЗЕРА
    @PostMapping
    public String create (@RequestParam("roles") String role ,@ModelAttribute("user") User user) {
        user.setRoles(userService.findRolesByName(role));
        userService.saveUser(user);
        return "redirect:/admin";
    }
    // ИЗМЕНИТЬ ЮЗЕРА
    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") long id) {
        model.addAttribute("allRoles", userService.getAllRoles());
        model.addAttribute("user", userService.getUserById(id));
        return "admin/edit";
    }
    // СОХРАНИТЬ ИЗМЕНЕННОГО ЮЗЕРА
    @PatchMapping("/{id}")
    public String update(@RequestParam("roles") String role, @ModelAttribute("user") User user, @PathVariable("id") long id) {
        user.setRoles(userService.findRolesByName(role));
        userService.saveUser(user);
        return "redirect:/admin";
    }
    // УДАЛИТЬ ЮЗЕРА
    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable("id") long id) {
        userService.removeUserById(id);

        return "redirect:/admin";
    }

}
