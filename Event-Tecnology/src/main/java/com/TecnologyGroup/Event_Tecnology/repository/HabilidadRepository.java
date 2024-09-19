package com.TecnologyGroup.Event_Tecnology.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.TecnologyGroup.Event_Tecnology.model.entity.Habilidad;

import java.util.Optional;

public interface HabilidadRepository extends JpaRepository<Habilidad, Integer> {

    Optional<Habilidad> findByHabilidadNombre(String habilidadNombre);

    boolean existsByHabilidadNombre(String habilidadNombre);

}
