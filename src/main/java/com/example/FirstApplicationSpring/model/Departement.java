package com.example.FirstApplicationSpring.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Set;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@Table(name = "Departement")
public class Departement implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idDepart")
    private int idDepart;
    @Column(name = "nomDepart")
    private String nomDepart;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "departement")
    private Set<Etudiant> etudiants;

}
