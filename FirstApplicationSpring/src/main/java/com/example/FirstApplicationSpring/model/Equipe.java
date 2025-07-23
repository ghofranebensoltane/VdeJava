package com.example.FirstApplicationSpring.model;

import com.example.FirstApplicationSpring.enums.Niveau;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Set;


@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@Table(name = "Equipe")
public class Equipe implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idEquipe")
    private int idEquipe;
    @Column(name = "nomEquipe")
    private String nomEquipe;

    @Enumerated(EnumType.STRING)
    @Column(name = "niveau")
    private Niveau niveau;

    @OneToOne(cascade = CascadeType.ALL)
    private DetailEquipe detailEquipe;

    @ManyToMany (mappedBy = "equipes")
    @JsonIgnore
    private Set<Etudiant> etudiantSet;
}
