package com.TecnologyGroup.Event_Tecnology.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.TecnologyGroup.Event_Tecnology.model.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    @Query("SELECT u FROM User u WHERE u.userEmail = :email")
    Optional<User> findAnyUserByEmail(@Param("email") String email);

    @Query("SELECT u FROM User u WHERE u.isDeleted = false")
    List<User> findAllActiveUsers();

    @Query("SELECT u FROM User u WHERE u.emailVerified = :verified AND u.isDeleted = false")
    List<User> findAllWithVerifiedEmail(@Param("verified") boolean verified);

    @Query("SELECT u FROM User u WHERE u.rol.nombreRol = :rolNombre AND u.isDeleted = false")
    List<User> findByRol(@Param("rolNombre") String rolNombre);

    @Query("SELECT u FROM User u WHERE u.createAt BETWEEN :startDate AND :endDate AND u.isDeleted = false")
    List<User> findByRegistrationDateBetween(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

    @Query("SELECT u FROM User u WHERE u.isDeleted = true")
    List<User> findAllDeletedUsers();

}
