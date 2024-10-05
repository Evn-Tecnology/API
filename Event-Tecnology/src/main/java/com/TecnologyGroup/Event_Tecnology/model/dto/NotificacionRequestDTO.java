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
    private Integer userId;

    @NotBlank
    private String mensaje;

    @NotNull
    private TipoNotificacion tipo;

    private Integer eventId;

    @NotNull
    private EstadoNotificacion estado;
}
