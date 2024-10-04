package com.TecnologyGroup.Event_Tecnology.model.dto;

import com.TecnologyGroup.Event_Tecnology.model.enums.CategoriaEvento;
import com.TecnologyGroup.Event_Tecnology.model.enums.EstadoEvento;
import com.TecnologyGroup.Event_Tecnology.model.enums.TipoEvento;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventResponseDTO {

    private Integer id;
    private String eventNombre;
    private CategoriaEvento categoriaEvento;
    private String eventDescripcion;
    private LocalDate eventFecha;
    private LocalTime eventHora;
    private LocalDateTime eventFechaFin;
    private String eventLugar;
    private int capacidad;
    private int numInscripciones;
    private EstadoEvento estado;
    private boolean esPagado;
    private BigDecimal precio;
    private TipoEvento tipoEvento;

    private String organizadorPrincipal;
}
