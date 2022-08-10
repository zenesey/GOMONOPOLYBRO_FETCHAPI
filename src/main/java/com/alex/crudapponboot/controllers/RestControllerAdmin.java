package com.alex.crudapponboot.controllers;


import com.alex.crudapponboot.models.User;
import com.alex.crudapponboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
    public List<User> allUsers(@ModelAttribute("user") User user) {
        return userService.getAllUsers();
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
    public ResponseEntity<String> saveUser(@RequestBody @Valid User user, BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
        System.out.println("sosat");
    }

        userService.saveUser(user);
        return ResponseEntity.ok("valid");
    }

}
