package com.TecnologyGroup.Event_Tecnology.model.entity;

import com.TecnologyGroup.Event_Tecnology.model.enums.EstadoResena;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "resenas")
public class Resena {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User usuario;

    @ManyToOne
    @JoinColumn(name = "event_id", nullable = false)
    private Event evento;

    @Column(name = "comentario", nullable = false, length = 500)
    private String comentario;

    @Column(name = "puntuacion", nullable = false)
    private int puntuacion;

    @Column(name = "fecha_publicacion", nullable = false)
    private LocalDateTime fechaPublicacion;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado", nullable = false)
    private EstadoResena estado;
}
