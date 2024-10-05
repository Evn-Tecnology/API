package com.TecnologyGroup.Event_Tecnology.model.dto;

import com.TecnologyGroup.Event_Tecnology.model.enums.EstadoInscripcion;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InscripcionResponseDTO {

    private Integer id;  // ID de la inscripción
    private Integer eventId;  // ID del evento
    private String eventNombre;  // Nombre del evento
    private Integer userId;  // ID del usuario
    private String userNombre;  // Nombre del usuario
    private LocalDateTime fechaInscripcion;  // Fecha en que se realizó la inscripción
    private EstadoInscripcion estado;  // Estado de la inscripción
    private boolean asistenciaConfirmada;  // Indica si la asistencia ha sido confirmada
    private boolean esGratuito;  // Indica si la inscripción fue gratuita

}
