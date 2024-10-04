package com.TecnologyGroup.Event_Tecnology.model.dto;

import com.TecnologyGroup.Event_Tecnology.model.enums.CategoriaEvento;
import com.TecnologyGroup.Event_Tecnology.model.enums.TipoEvento;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventRequestDTO {

    @NotNull
    @Size(min = 5, max = 100)
    private String eventNombre;

    @NotNull
    private CategoriaEvento categoriaEvento;

    @NotNull
    private String eventDescripcion;

    @NotNull
    private LocalDate eventFecha;

    @NotNull
    private LocalTime eventHora;

    private LocalDate eventFechaFin;

    @NotNull
    private String eventLugar;

    @NotNull
    private int capacidad;

    private boolean esPagado;

    private BigDecimal precio;

    @NotNull
    private TipoEvento tipoEvento;
}
