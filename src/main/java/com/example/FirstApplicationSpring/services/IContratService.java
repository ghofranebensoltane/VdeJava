package com.example.FirstApplicationSpring.services;

import com.example.FirstApplicationSpring.dto.ContratDTO;
import com.example.FirstApplicationSpring.model.Contrat;

import java.time.LocalDate;
import java.util.List;

public interface IContratService {
    List<Contrat> getAllContrats();
    Contrat updateContrat (Contrat  c);
    Contrat addContrat (Contrat c);
    Contrat getOneContrat (int  idContrat);
    void removeContrat(int idContrat);
    ContratDTO affectContratToEtudiant(Contrat c, String nomEtudiant , String prenomEtudiant);
    String retrieveAndUpdateStatusContrat();
}
