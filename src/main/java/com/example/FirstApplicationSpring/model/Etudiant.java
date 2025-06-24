package com.example.FirstApplicationSpring.model;

import com.example.FirstApplicationSpring.enums.Option;
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
    private Set<Contrat> contrats;

   @ManyToOne
    private Departement departement;

   @ManyToMany(fetch = FetchType.LAZY)
    private Set<Equipe> equipes;
}
