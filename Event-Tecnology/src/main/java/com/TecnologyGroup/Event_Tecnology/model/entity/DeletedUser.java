package com.TecnologyGroup.Event_Tecnology.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "deleted_users")
public class DeletedUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "user_nombre", nullable = false)
    private String userNombre;

    @Column(name = "user_apellido", nullable = false)
    private String userApellido;

    @Column(name = "user_email", nullable = false, unique = true)
    private String userEmail;

    @Column(name = "user_telefono")
    private String userTelefono;

    @Column(name = "deleted_at", nullable = false)
    private LocalDate deletedAt;
}











