package com.TecnologyGroup.Event_Tecnology.model.dto;

import com.TecnologyGroup.Event_Tecnology.model.enums.MetodoPago;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PagoResponseDTO {

    private Integer id;
    private Integer userId;
    private String userNombre;
    private Integer eventId;
    private String eventNombre;
    private BigDecimal monto;
    private MetodoPago metodoPago;
    private LocalDateTime fechaPago;
}
