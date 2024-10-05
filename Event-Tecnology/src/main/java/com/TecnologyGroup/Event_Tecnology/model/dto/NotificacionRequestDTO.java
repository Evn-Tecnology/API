package com.TecnologyGroup.Event_Tecnology.model.dto;

import com.TecnologyGroup.Event_Tecnology.model.enums.EstadoNotificacion;
import com.TecnologyGroup.Event_Tecnology.model.enums.TipoNotificacion;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NotificacionRequestDTO {

    @NotNull
    private Integer userId;  // ID del usuario que recibirá la notificación

    @NotBlank
    private String mensaje;  // Mensaje de la notificación

    @NotNull
    private TipoNotificacion tipo;  // Tipo de notificación (evento, sistema, pago, etc.)

    // Si la notificación está relacionada con un evento, puede incluirse el ID del evento
    private Integer eventId;  // ID opcional del evento relacionado (si aplica)

    @NotNull
    private EstadoNotificacion estado;  // Estado de la notificación (LEIDA, NO_LEIDA)
}
