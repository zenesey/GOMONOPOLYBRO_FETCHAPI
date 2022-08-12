package com.alex.crudapponboot.service;


import com.alex.crudapponboot.dto.UserDto;
import com.alex.crudapponboot.models.Role;
import com.alex.crudapponboot.models.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

public interface UserService {
    List<UserDto> getAllUsersWithoutRoles();
    List<User> getAllUsersWithRoles();


    UserDto getUserWithoutRolesById(Long id);

    User getUserWithRolesById(Long id);


    void removeUserById(long id);

    void saveUser(User user);

    User getUserByUsername(String username);

    void updateUserById(long id, User user);








//    public Set<Role> findRolesById (String RoleIds)
}
