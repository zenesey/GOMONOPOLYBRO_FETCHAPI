package com.alex.crudapponboot.repositories;

import com.alex.crudapponboot.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

//ПОМОГИТЕ МОЙ МЕНТОР ТИРАН
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findUserByName(String name);
}
