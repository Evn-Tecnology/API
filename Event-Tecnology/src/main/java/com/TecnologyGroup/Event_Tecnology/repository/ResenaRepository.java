package com.TecnologyGroup.Event_Tecnology.repository;

import com.TecnologyGroup.Event_Tecnology.model.entity.Resena;
import com.TecnologyGroup.Event_Tecnology.model.enums.EstadoResena;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ResenaRepository extends JpaRepository<Resena, Integer> {

    List<Resena> findByUsuario_Id(Integer userId);

    List<Resena> findByEvento_Id(Integer eventId);

    List<Resena> findByEstado(EstadoResena estado);

    List<Resena> findByPuntuacion(int puntuacion);

    List<Resena> findByEvento_IdAndEstado(Integer eventId, EstadoResena estado);

    List<Resena> findByUsuario_IdAndEstado(Integer userId, EstadoResena estado);
}
