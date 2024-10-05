package com.TecnologyGroup.Event_Tecnology.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "intereres_tecnologicos")
public class InteresTecnologico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "interesTec_nombre", nullable = false, unique = true)
    private String interesTecNombre;
}