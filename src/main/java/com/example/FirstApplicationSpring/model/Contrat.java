package com.example.FirstApplicationSpring.model;


import com.example.FirstApplicationSpring.enums.Specialite;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;


@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@Table(name = "Contrat")
public class Contrat implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idContrat")
    private int idContrat;

    @Column (name = "dateDebutContrat")
    private Date dateDebutContrat;
    @Column (name = "dateFinContrat")
    private Date dateFinContrat;
    @Column (name = "archive")
    private boolean archive;
    @Column (name = "montantContrat")
    private int montantContrat;

    @Enumerated(EnumType.STRING)
    @Column(name = "specialite")
    private Specialite specialite;

    @ManyToOne
    @JsonIgnore
    private Etudiant etudiant;

}
