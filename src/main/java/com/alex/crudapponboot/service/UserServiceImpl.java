package com.alex.crudapponboot.service;

import com.alex.crudapponboot.models.User;
import com.alex.crudapponboot.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Transactional
    @Override
    public User getUserById(long id) {
        return userRepository.findById(id).get();
    }

    @Transactional
    @Override
    public void removeUserById(long id) {
        userRepository.deleteById(id);
    }
    @Transactional
    @Override
    public void updateUserById(long id, User user) {
        User userUpd = userRepository.findById(id).get();
        userUpd = user;
        userRepository.save(userUpd);

    }
    @Transactional
    @Override
    public void saveUser(User user) {
        userRepository.save(user);
    }
}
