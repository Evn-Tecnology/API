package com.TecnologyGroup.Event_Tecnology.repository;

import com.TecnologyGroup.Event_Tecnology.model.entity.Resena;
import com.TecnologyGroup.Event_Tecnology.model.enums.EstadoResena;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ResenaRepository extends JpaRepository<Resena, Integer> {

    // Consultar reseñas por usuario
    List<Resena> findByUsuario_Id(Integer userId);

    // Consultar reseñas por evento
    List<Resena> findByEvento_Id(Integer eventId);

    // Consultar reseñas por estado (pendiente, aprobada, rechazada)
    List<Resena> findByEstado(EstadoResena estado);

    // Consultar reseñas con una puntuación específica
    List<Resena> findByPuntuacion(int puntuacion);

    // Consultar reseñas por evento y estado
    List<Resena> findByEvento_IdAndEstado(Integer eventId, EstadoResena estado);

    // Consultar reseñas por usuario y estado
    List<Resena> findByUsuario_IdAndEstado(Integer userId, EstadoResena estado);
}
