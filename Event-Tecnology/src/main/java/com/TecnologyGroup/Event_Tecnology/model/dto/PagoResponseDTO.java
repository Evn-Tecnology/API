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

    private Integer id;  // ID del pago
    private Integer userId;  // ID del usuario que realizó el pago
    private String userNombre;  // Nombre del usuario
    private Integer eventId;  // ID del evento asociado al pago
    private String eventNombre;  // Nombre del evento
    private BigDecimal monto;
    private MetodoPago metodoPago;
    private LocalDateTime fechaPago;
}
