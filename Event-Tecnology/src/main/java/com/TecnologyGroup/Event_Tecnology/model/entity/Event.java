package com.TecnologyGroup.Event_Tecnology.model.entity;

import com.TecnologyGroup.Event_Tecnology.model.enums.CategoriaEvento;
import com.TecnologyGroup.Event_Tecnology.model.enums.EstadoEvento;
import com.TecnologyGroup.Event_Tecnology.model.enums.TipoEvento;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "events")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @Size(min = 5, max = 100)
    @Column(name = "event_nombre", nullable = false)
    private String event_nombre;

    @Enumerated(EnumType.STRING)
    @Column(name = "categoria_evento", nullable = false)
    private CategoriaEvento categoriaEvento;

    @NotNull
    @Column(name = "event_descripcion", nullable = false)
    private String event_description;

    @NotNull
    @Column(name = "event_lugar", nullable = false)
    private String eventLugar;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado_evento", nullable = false)
    private EstadoEvento estado;

    @ManyToOne
    @JoinColumn(name = "organizer_id", nullable = false)
    private User organizadorPrincipal;

    @ManyToMany
    @JoinTable(name = "event_co_organizers", joinColumns = @JoinColumn(name = "event_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Set<User> coorganizadores;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_evento", nullable = false)
    private TipoEvento tipoEvento;

}