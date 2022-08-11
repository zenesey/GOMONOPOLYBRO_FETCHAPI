package com.alex.crudapponboot.controllers;


import com.alex.crudapponboot.models.User;
import com.alex.crudapponboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/admin")

public class RestControllerAdmin {


    private final UserService userService;
    @Autowired
    public RestControllerAdmin(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public List<User> allUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/auth")
    public User principal(@AuthenticationPrincipal User user) {
        return user;
    }



    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable long id) {
        userService.removeUserById(id);
        return "ok";
    }

    @PutMapping
    public String update(@RequestBody User user) {

        userService.updateUserById(user.getId(), user);
        return "fuck";
    }


    @PostMapping
    public ResponseEntity<String> saveUser(@RequestBody User user) {
        userService.saveUser(user);
        return ResponseEntity.ok("valid");
    }

}
