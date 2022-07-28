package com.alex.crudapponboot.service;


import com.alex.crudapponboot.models.Role;
import com.alex.crudapponboot.models.User;
import org.springframework.stereotype.Service;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();

    User getUserById(long id);

    void removeUserById(long id);

    void saveUser(User user);

    User getUserByUsername(String username);

    public List<Role> getAllRoles();

    public List<Role> findRolesByName (String RoleIds);
}
