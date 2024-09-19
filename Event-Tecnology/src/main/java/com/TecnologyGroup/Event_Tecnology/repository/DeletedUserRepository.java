package com.TecnologyGroup.Event_Tecnology.repository;

import com.TecnologyGroup.Event_Tecnology.model.entity.DeletedUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface DeletedUserRepository extends JpaRepository<DeletedUser, Integer> {

    @Query("SELECT d FROM DeletedUser d WHERE d.userEmail = :email")
    DeletedUser findByEmail(@Param("email") String email);

    @Query("SELECT d FROM DeletedUser d WHERE d.deletedAt BETWEEN :startDate AND :endDate")
    List<DeletedUser> findByDeletedAtBetween(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

    boolean existsByUserEmail(String email);
}