package com.TecnologyGroup.Event_Tecnology.repository;

import com.TecnologyGroup.Event_Tecnology.model.entity.Rol;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RolRepository extends JpaRepository<Rol, Integer> {

    Optional<Rol> findByNombreRol(String rolNombre);

    boolean existsByNombreRol(String rolNombre);

}