package com.TecnologyGroup.Event_Tecnology.repository;

import com.TecnologyGroup.Event_Tecnology.model.entity.Notificacion;
import com.TecnologyGroup.Event_Tecnology.model.enums.EstadoNotificacion;
import com.TecnologyGroup.Event_Tecnology.model.enums.TipoNotificacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface NotificacionRepository extends JpaRepository<Notificacion, Integer> {

    List<Notificacion> findByUsuario_Id(Integer userId);

    List<Notificacion> findByEstado(EstadoNotificacion estado);

    List<Notificacion> findByTipo(TipoNotificacion tipo);

    List<Notificacion> findByEvento_Id(Integer eventId);

    @Query("SELECT n FROM Notificacion n WHERE n.usuario.id = :userId AND n.estado = 'NO_LEIDA'")
    List<Notificacion> findNotificacionesNoLeidasPorUsuario(@Param("userId") Integer userId);
}
