package com.TecnologyGroup.Event_Tecnology.repository;

import com.TecnologyGroup.Event_Tecnology.model.entity.DetailUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface DetailUserRepository extends JpaRepository<DetailUser, Integer> {

    @Query("SELECT d FROM DetailUser d JOIN d.interesTecnologicos i WHERE i.interesTecNombre = :interes")
    List<DetailUser> findByInteresTecnologico(@Param("interes") String interesTecNombre);

    @Query("SELECT d FROM DetailUser d JOIN d.habilidades h WHERE h.habilidadNombre = :habilidad")
    List<DetailUser> findByHabilidad(@Param("habilidad") String habilidadNombre);

    Optional<DetailUser> findByUser_Id(Integer userId);

    @Query("SELECT CASE WHEN COUNT(d) > 0 THEN TRUE ELSE FALSE END FROM DetailUser d WHERE d.urlLinkedIn = :urlLinkedIn")
    boolean existsByUrlLinkedIn(@Param("urlLinkedIn") String urlLinkedIn);
}
