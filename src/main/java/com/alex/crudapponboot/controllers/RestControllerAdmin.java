package com.alex.crudapponboot.controllers;

import com.alex.crudapponboot.models.User;
import com.alex.crudapponboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
    public ResponseEntity<List<User>> allUsers() {
        return ResponseEntity.ok(userService.getAllUsersWithRoles());

    }

    @GetMapping("/auth")
    public ResponseEntity<User> principal(@AuthenticationPrincipal User user) {
        return ResponseEntity.ok(user);
    }



    @DeleteMapping("/{id}asdasd")
    public ResponseEntity<?> deleteUser(@PathVariable long id) {
        userService.removeUserById(id);
        return ResponseEntity.ok("Delete successful");
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody User user) {
        userService.updateUserById(user.getId(), user);
        return ResponseEntity.ok("Update successful");
    }

    @PostMapping
    public ResponseEntity<String> saveUser(@RequestBody User user) {
        userService.saveUser(user);
        return ResponseEntity.ok("Save successful");
    }
}
