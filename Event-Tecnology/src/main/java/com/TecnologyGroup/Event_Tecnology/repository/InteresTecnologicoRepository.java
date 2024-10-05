package com.TecnologyGroup.Event_Tecnology.repository;

import com.TecnologyGroup.Event_Tecnology.model.entity.InteresTecnologico;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface InteresTecnologicoRepository extends JpaRepository<InteresTecnologico, Integer> {

    Optional<InteresTecnologico> findByInteresTecNombre(String interesTecNombre);

    boolean existsByInteresTecNombre(String interesTecNombre);

    List<InteresTecnologico> findAllByInteresTecNombreIn(List<String> nombres);

}
