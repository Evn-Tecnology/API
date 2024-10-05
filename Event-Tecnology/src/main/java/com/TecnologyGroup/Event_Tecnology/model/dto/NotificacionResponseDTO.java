package com.TecnologyGroup.Event_Tecnology.model.dto;

import com.TecnologyGroup.Event_Tecnology.model.enums.EstadoNotificacion;
import com.TecnologyGroup.Event_Tecnology.model.enums.TipoNotificacion;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NotificacionResponseDTO {

    private Integer id;  // ID de la notificación
    private String mensaje;  // Mensaje de la notificación
    private LocalDateTime fechaCreacion;  // Fecha en que se creó la notificación
    private EstadoNotificacion estado;  // Estado de la notificación (leída/no leída)
    private TipoNotificacion tipo;  // Tipo de notificación (evento, sistema, etc.)

    // Información adicional del usuario que recibió la notificación
    private Integer userId;
    private String userNombre;

    // Información opcional del evento relacionado, si aplica
    private Integer eventId;
    private String eventNombre;

}
