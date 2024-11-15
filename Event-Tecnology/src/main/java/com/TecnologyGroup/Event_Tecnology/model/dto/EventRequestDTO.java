package com.TecnologyGroup.Event_Tecnology.model.dto;

import com.TecnologyGroup.Event_Tecnology.model.enums.CategoriaEvento;
import com.TecnologyGroup.Event_Tecnology.model.enums.TipoEvento;
import jakarta.validation.constraints.NotBlank;
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

    @NotNull(message = "El organizador es obligatorio")
    private Integer organizadorId;

    @NotBlank(message = "El nombre del evento no puede ser vacío")
    @Size(min = 5, max = 100)
    private String eventNombre;

    @NotBlank(message = "La categoría no puede ser vacío")
    @Size(min = 2, message = "La categoría debe tener al menos 2 caracteres")
    private String categoriaEvento;

    @NotBlank(message = "La descripcion no puede ser vacío")
    @Size(min = 2, message = "La descripcion debe tener al menos 2 caracteres")
    private String eventDescripcion;

    @NotNull
    private LocalDate eventFecha;

    @NotNull
    private LocalTime eventHora;

    @NotNull
    private LocalDate eventFechaFin;

    @NotBlank(message = "El lugar no puede ser vacío")
    @Size(min = 2, message = "El lugar debe tener al menos 2 caracteres")
    private String eventLugar;

    @NotNull
    private int capacidad;

    @NotNull
    private boolean esPagado;

    private BigDecimal precio;

    @NotNull
    private String tipoEvento;
}
