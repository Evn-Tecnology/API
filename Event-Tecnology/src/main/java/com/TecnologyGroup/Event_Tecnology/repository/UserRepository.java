package com.TecnologyGroup.Event_Tecnology.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.TecnologyGroup.Event_Tecnology.model.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByUserEmail(String email);

    boolean existsByUserEmail(String email);

    @Query("SELECT u FROM User u WHERE u.emailVerified = :verified")
    List<User> findAllWithVerifiedEmail(@Param("verified") boolean verified);

    @Query("SELECT u FROM User u WHERE u.rol.nombreRol = :rolNombre")
    List<User> findByRol(@Param("rolNombre") String rolNombre);

    @Query("SELECT u FROM User u WHERE u.createAt BETWEEN :startDate AND :endDate")
    List<User> findByRegistrationDateBetween(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);
}