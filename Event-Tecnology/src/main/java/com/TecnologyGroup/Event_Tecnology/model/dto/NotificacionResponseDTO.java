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

    private Integer id;
    private String mensaje;
    private LocalDateTime fechaCreacion;
    private EstadoNotificacion estado;
    private TipoNotificacion tipo;

    private Integer userId;
    private String userNombre;

    private Integer eventId;
    private String eventNombre;

}
