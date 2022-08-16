package com.alex.crudapponboot.service;


import com.alex.crudapponboot.dto.UserDto;
import com.alex.crudapponboot.models.Role;
import com.alex.crudapponboot.models.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

public interface UserService {
    List<User> getAllUsersWithRoles();
    User getUserWithRolesById(Long id);
    void removeUserById(long id);
    void saveUser(User user);
    User getUserByEmail(String email);
    void updateUserById(long id, User user);

}
