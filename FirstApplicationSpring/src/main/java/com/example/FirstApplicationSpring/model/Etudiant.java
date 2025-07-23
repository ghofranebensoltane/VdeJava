package com.example.FirstApplicationSpring.model;

import com.example.FirstApplicationSpring.enums.Option;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Set;


@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @ToString
public class Etudiant implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idEtudiant")
    private int idEtudiant;

    @Column(name = "prenom")
    private String prenomEtudiant;

    @Column(name = "nom")
    private String nomEtudiant;

    @Enumerated(EnumType.STRING)
    @Column(name = "etudiant_option")
    private Option option;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "etudiant")
    @JsonIgnore
    private Set<Contrat> contrats;

   @ManyToOne
   @JsonIgnore
    private Departement departement;

   @ManyToMany(fetch = FetchType.LAZY)
   @JsonIgnore
    private Set<Equipe> equipes;
}
