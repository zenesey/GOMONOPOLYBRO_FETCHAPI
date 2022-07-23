package com.alex.crudapponboot.service;


import com.alex.crudapponboot.models.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();

    User getUserById(long id);

    void removeUserById(long id);

    void updateUserById(long id, User user);

    void saveUser(User user);


}
