package com.TecnologyGroup.Event_Tecnology.repository;

import com.TecnologyGroup.Event_Tecnology.model.entity.Inscripcion;
import com.TecnologyGroup.Event_Tecnology.model.enums.EstadoInscripcion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface InscripcionRepository extends JpaRepository<Inscripcion, Integer> {

    // Consultar inscripciones por evento
    List<Inscripcion> findByEvento_Id(Integer eventId);

    // Consultar inscripciones por usuario
    List<Inscripcion> findByUsuario_Id(Integer userId);

    // Consultar inscripciones por estado
    List<Inscripcion> findByEstado(EstadoInscripcion estado);

    // Consultar inscripciones por asistencia confirmada
    List<Inscripcion> findByAsistenciaConfirmada(boolean asistenciaConfirmada);

    // Consultar inscripciones por evento y asistencia confirmada
    @Query("SELECT i FROM Inscripcion i WHERE i.evento.id = :eventId AND i.asistenciaConfirmada = true")
    List<Inscripcion> findAsistenciasConfirmadasPorEvento(@Param("eventId") Integer eventId);
}
