package com.TecnologyGroup.Event_Tecnology.repository;

import com.TecnologyGroup.Event_Tecnology.model.entity.Notificacion;
import com.TecnologyGroup.Event_Tecnology.model.enums.EstadoNotificacion;
import com.TecnologyGroup.Event_Tecnology.model.enums.TipoNotificacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface NotificacionRepository extends JpaRepository<Notificacion, Integer> {

    // Consultar notificaciones por usuario
    List<Notificacion> findByUsuario_Id(Integer userId);

    // Consultar notificaciones por estado (ej: leída/no leída)
    List<Notificacion> findByEstado(EstadoNotificacion estado);

    // Consultar notificaciones por tipo
    List<Notificacion> findByTipo(TipoNotificacion tipo);

    // Consultar notificaciones asociadas a un evento específico
    List<Notificacion> findByEvento_Id(Integer eventId);

    // Consultar notificaciones no leídas de un usuario
    @Query("SELECT n FROM Notificacion n WHERE n.usuario.id = :userId AND n.estado = 'NO_LEIDA'")
    List<Notificacion> findNotificacionesNoLeidasPorUsuario(@Param("userId") Integer userId);
}
