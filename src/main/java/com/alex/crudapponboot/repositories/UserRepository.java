package com.alex.crudapponboot.repositories;

import com.alex.crudapponboot.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
//ПОМОГИТЕ МОЙ МЕНТОР ТИРАН
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
   User findByUsername(String username);

   @Query(value = "SELECT u FROM User u join fetch u.roles where :id=u.id")
   User getUserWithRolesById(@Param("id") Long id);
}
