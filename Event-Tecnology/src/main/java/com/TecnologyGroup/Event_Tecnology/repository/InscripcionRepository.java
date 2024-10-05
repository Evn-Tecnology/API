package com.TecnologyGroup.Event_Tecnology.repository;

import com.TecnologyGroup.Event_Tecnology.model.entity.Inscripcion;
import com.TecnologyGroup.Event_Tecnology.model.enums.EstadoInscripcion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface InscripcionRepository extends JpaRepository<Inscripcion, Integer> {

    List<Inscripcion> findByEvento_Id(Integer eventId);

    List<Inscripcion> findByUsuario_Id(Integer userId);

    List<Inscripcion> findByEstado(EstadoInscripcion estado);

    List<Inscripcion> findByAsistenciaConfirmada(boolean asistenciaConfirmada);

    @Query("SELECT i FROM Inscripcion i WHERE i.evento.id = :eventId AND i.asistenciaConfirmada = true")
    List<Inscripcion> findAsistenciasConfirmadasPorEvento(@Param("eventId") Integer eventId);
}
