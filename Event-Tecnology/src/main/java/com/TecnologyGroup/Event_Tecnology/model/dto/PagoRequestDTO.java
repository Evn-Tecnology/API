package com.TecnologyGroup.Event_Tecnology.model.dto;

import com.TecnologyGroup.Event_Tecnology.model.enums.MetodoPago;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PagoRequestDTO {

    @NotNull
    private Integer userId;

    @NotNull
    private Integer eventId;

    @NotNull
    private Integer inscripcionId;

    @NotNull
    private BigDecimal monto;

    @NotNull
    private MetodoPago metodoPago;

}
