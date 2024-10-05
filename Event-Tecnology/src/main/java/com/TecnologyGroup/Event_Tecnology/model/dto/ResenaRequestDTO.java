package com.TecnologyGroup.Event_Tecnology.model.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResenaRequestDTO {

    @NotNull
    private Integer userId;

    @NotNull
    private Integer eventId;

    @NotBlank
    private String comentario;

    @NotNull
    @Min(1)
    @Max(5)
    private int puntuacion;

}
