package com.TecnologyGroup.Event_Tecnology.repository;

import com.TecnologyGroup.Event_Tecnology.model.entity.Habilidad;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface HabilidadRepository extends JpaRepository<Habilidad, Integer> {

    Optional<Habilidad> findByHabilidadNombre(String habilidadNombre);

    boolean existsByHabilidadNombre(String habilidadNombre);

    List<Habilidad> findAllByHabilidadNombreIn(List<String> nombres);
}
