package com.alex.crudapponboot.service;

import com.alex.crudapponboot.dao.UserDao;
import com.alex.crudapponboot.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class UserServiceImpl implements UserService{

    private UserDao userDao;
    @Autowired
    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }
    @Override
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    @Transactional
    @Override
    public User getUserById(long id) {
        return userDao.getUserById(id);
    }

    @Transactional
    @Override
    public void removeUserById(long id) {
        userDao.removeUserById(id);
    }
    @Transactional
    @Override
    public void updateUserById(long id, User user) {
        userDao.updateUserById(id, user);
    }
    @Transactional
    @Override
    public void saveUser(User user) {
        userDao.saveUser(user);
    }
}
