package com.example.FirstApplicationSpring.services;

import com.example.FirstApplicationSpring.model.Contrat;

import java.util.List;

public interface IContratService {
    List<Contrat> getAllContrats();
    Contrat updateContrat (Contrat  c);
    Contrat addContrat (Contrat c);
    Contrat getOneContrat (int  idContrat);
    void removeContrat(int idContrat);
}
