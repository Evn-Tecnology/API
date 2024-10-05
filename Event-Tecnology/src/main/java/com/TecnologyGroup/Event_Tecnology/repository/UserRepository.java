package com.TecnologyGroup.Event_Tecnology.repository;

import com.TecnologyGroup.Event_Tecnology.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    @Query("SELECT u FROM User u WHERE u.userEmail = :email")
    Optional<User> findAnyUserByEmail(@Param("email") String email);

    @Query("SELECT u FROM User u WHERE u.estado = 'ACTIVO'")
    List<User> findAllActiveUsers();

    @Query("SELECT u FROM User u WHERE u.emailVerified = :verified AND u.estado = 'ACTIVO'")
    List<User> findAllWithVerifiedEmail(@Param("verified") boolean verified);

    @Query("SELECT u FROM User u WHERE u.rol.nombreRol = :rolNombre AND u.estado = 'ACTIVO'")
    List<User> findByRol(@Param("rolNombre") String rolNombre);

    @Query("SELECT u FROM User u WHERE u.createAt BETWEEN :startDate AND :endDate AND u.estado = 'ACTIVO'")
    List<User> findByRegistrationDateBetween(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

    @Query("SELECT u FROM User u WHERE u.estado = 'ELIMINADO'")
    List<User> findAllDeletedUsers();

}
