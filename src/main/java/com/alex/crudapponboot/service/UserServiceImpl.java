package com.alex.crudapponboot.service;

import com.alex.crudapponboot.models.Role;
import com.alex.crudapponboot.models.User;
import com.alex.crudapponboot.repositories.RoleRepository;
import com.alex.crudapponboot.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;


@Service
public class UserServiceImpl implements UserService, UserDetailsService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    @Autowired
    public UserServiceImpl(UserRepository userRepository,
                           RoleRepository roleRepository) {

        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("USER SUQA NE NAIDEN");
        }
        return user;
    }


    @Override
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    @Override
    public List<Role> findRolesByName (String RoleIds) {
        List<Role> roles = new ArrayList<>();
        for (Role role : getAllRoles()){
            if (RoleIds.contains(String.valueOf(role.getId()))) {
                roles.add(role);
            }
        }
        return roles;
    }

    @Transactional
    @Override
    public void removeUserById(long id) {
        userRepository.deleteById(id);
    }

    @Transactional
    @Override
    public void saveUser(User user) {
        if (user.getPassword() == null) {
            user.setPassword(new BCryptPasswordEncoder().encode(getUserById(user.getId()).getPassword()));
        }
        user.setPassword(getUserById(user.getId()).getPassword());
        userRepository.save(user);
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

    @Override
    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }


}
