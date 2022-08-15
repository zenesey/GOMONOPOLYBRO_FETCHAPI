package com.alex.crudapponboot.repositories;

import com.alex.crudapponboot.dto.UserDto;
import com.alex.crudapponboot.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

//ПОМОГИТЕ МОЙ МЕНТОР ТИРАН
@Repository
public interface UserRepository extends JpaRepository<User, Long> {



   User findByUsername(String username);

   @Query(value = "SELECT u FROM User u join fetch u.roles where :id=u.id")
   User getUserWithRolesById(@Param("id") Long id);

   @Query(value = "SELECT u FROM User u join fetch u.roles")
   List<User> getAllUsersWithRoles();



   @Query(value = "SELECT new com.alex.crudapponboot.dto.UserDto(u.id, u.name, u.surName, u.age, u.username, u.password) FROM User u")
   List<UserDto> getAllUsersWithoutRoles();

   @Query(value = "SELECT new com.alex.crudapponboot.dto.UserDto(u.id, u.name, u.surName, u.age, u.username, u.password) from User u where u.id = :id")
   UserDto getUserWithoutRolesById(@Param("id") Long id);










//   @Query(value = "SELECT new com.alex.crudapponboot.dto.UserDto(u.id, u.name, u.surName, u.age, u.username, u.password) FROM User u where :id=u.id")
//   UserDto geaAllbezRoley(@Param("id") Long id);






}
