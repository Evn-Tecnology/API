package com.TecnologyGroup.Event_Tecnology.repository;

import com.TecnologyGroup.Event_Tecnology.model.entity.Event;
import com.TecnologyGroup.Event_Tecnology.model.enums.EstadoEvento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface EventRepository extends JpaRepository<Event, Integer> {

    // Consultar eventos por nombre (por coincidencia parcial)
    @Query("SELECT e FROM Event e WHERE LOWER(e.event_nombre) LIKE LOWER(CONCAT('%', :nombre, '%'))")
    List<Event> findByNombreContaining(@Param("nombre") String nombre);

    // Consultar eventos entre dos fechas
    @Query("SELECT e FROM Event e WHERE e.eventFecha BETWEEN :fechaInicio AND :fechaFin")
    List<Event> findByFechaBetween(@Param("fechaInicio") LocalDate fechaInicio, @Param("fechaFin") LocalDate fechaFin);

    // Consultar eventos por organizador
    List<Event> findByOrganizadorPrincipal_Id(Integer organizadorId);

    // Consultar eventos por estado
    List<Event> findByEstado(EstadoEvento estado);

    // Consultar eventos pagados
    List<Event> findByEsPagadoTrue();
}
