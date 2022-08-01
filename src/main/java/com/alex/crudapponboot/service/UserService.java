package com.alex.crudapponboot.service;


import com.alex.crudapponboot.models.Role;
import com.alex.crudapponboot.models.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

public interface UserService {
    List<User> getAllUsers();

    User getUserById(long id);

    void removeUserById(long id);

    void saveUser(User user);

    User getUserByUsername(String username);

    public void updateUserById(long id, User user);

    public List<Role> getAllRoles();

    public Set<Role> findRolesById (String RoleIds);
}
