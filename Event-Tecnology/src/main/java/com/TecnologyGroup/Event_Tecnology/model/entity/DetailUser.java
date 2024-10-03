package com.TecnologyGroup.Event_Tecnology.model.entity;

import jakarta.persistence.*;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "detail_users")
public class DetailUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "detailuser_interes", joinColumns = @JoinColumn(name = "detailuser_id"), inverseJoinColumns = @JoinColumn(name = "interes_id"))
    private List<InteresTecnologico> interesTecnologicos;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "detailuser_habilidad", joinColumns = @JoinColumn(name = "detailuser_id"), inverseJoinColumns = @JoinColumn(name = "habilidad_id"))
    private List<Habilidad> habilidades;

    @Column(name = "url_linkedin", unique = true)
    private String urlLinkedIn;

    @Column(name = "edad", updatable = false)
    private int edad = 0;

    @Column(name = "genero")
    private String genero;

    @Column(name = "pais")
    private String pais;

    @OneToOne(mappedBy = "userDetail", cascade = CascadeType.ALL)
    private User user;
}