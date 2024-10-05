package com.TecnologyGroup.Event_Tecnology.model.entity;

import com.TecnologyGroup.Event_Tecnology.model.enums.EstadoInscripcion;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "inscripciones")
public class Inscripcion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "event_id", nullable = false)
    private Event evento;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User usuario;

    @Column(name = "fecha_inscripcion", nullable = false)
    private LocalDateTime fechaInscripcion;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado", nullable = false)
    private EstadoInscripcion estado;

    @Column(name = "asistencia_confirmada", nullable = false)
    private boolean asistenciaConfirmada;

    @Column(name = "es_gratuito", nullable = false)
    private boolean esGratuito;

    @OneToMany(mappedBy = "inscripcion", cascade = CascadeType.ALL)
    private Set<Pago> pagos;
}
