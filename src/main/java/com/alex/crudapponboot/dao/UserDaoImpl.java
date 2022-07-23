package com.alex.crudapponboot.dao;


import com.alex.crudapponboot.models.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;


@Repository
public class UserDaoImpl implements UserDao{
    @PersistenceContext
    private EntityManager entityManager;

    public EntityManager getEntityManager() {
        return this.entityManager;
    }


    @Override
    public List<User> getAllUsers() {
        return getEntityManager().createQuery("SELECT u FROM User u", User.class).getResultList();
    }

    @Override
    public User getUserById(long id) {
        return getEntityManager().find(User.class,id);
    }

    @Override

    public void removeUserById(long id) {
        getEntityManager().remove(getUserById(id));
    }

    @Override
    public void updateUserById(long id, User user) {
        getEntityManager().merge(user);

    }

    @Override
    public void saveUser(User user) {
        getEntityManager().persist(user);

    }
}
