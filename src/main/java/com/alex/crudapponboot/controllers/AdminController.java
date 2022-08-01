package com.alex.crudapponboot.controllers;


import com.alex.crudapponboot.models.User;
import com.alex.crudapponboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final UserService userService;

    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }


    // 1 приц
    @GetMapping
    public String getAllUsers(@AuthenticationPrincipal User authUser, @ModelAttribute("user") User user, Model model) {
        model.addAttribute("auth",authUser);
        model.addAttribute("allRoles", userService.getAllRoles());
        model.addAttribute("allUsers", userService.getAllUsers());
        return "admin/firsview";
    }


    @PostMapping
    public String saveUser(@RequestParam ("roles") String role, @ModelAttribute("user") User user) {
        if(role!=null) {
            user.setRoles(userService.findRolesById(role));
        }
        user.setRoles(userService.findRolesById(role));
        userService.saveUser(user);
        return "redirect:/admin";
    }



    @PatchMapping
    public String update(@RequestParam(value = "roles",required = false) String role, @ModelAttribute("user") User user) {
        System.out.println(role);

        if(role!=null) {
            user.setRoles(userService.findRolesById(role));
        }
        userService.updateUserById(user.getId(), user);
        return "redirect:/admin";
    }

    @DeleteMapping
    public String deleteUser(@RequestParam("id") long id) {
        userService.removeUserById(id);

        return "redirect:/admin";
    }
}
























