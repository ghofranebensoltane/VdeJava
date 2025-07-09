package com.example.FirstApplicationSpring.dto;

import com.example.FirstApplicationSpring.model.Contrat;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Builder
public class ContratDTO {
    private Contrat contrat;
    private String nomEtudiant;
    private String prenomEtudiant;

}
