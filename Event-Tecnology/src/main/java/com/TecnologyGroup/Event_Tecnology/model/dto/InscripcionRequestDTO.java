package com.TecnologyGroup.Event_Tecnology.model.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InscripcionRequestDTO {

    @NotNull
    private Integer eventId;

    @NotNull
    private Integer userId;

    @NotNull
    private boolean esGratuito;

}
