package com.example.FirstApplicationSpring.services;

import com.example.FirstApplicationSpring.model.Contrat;
import com.example.FirstApplicationSpring.model.Etudiant;

import java.util.List;

public interface IEtudiantService {
    List<Etudiant> getAllEtudiants();
    Etudiant addEtudiant(Etudiant e);
    Etudiant updateEtudiant(Etudiant e);
    Etudiant getEtudiant(int idEtudiant);
    void removeEtudiant(int idEtudiant);
    public void assignEtudiantToDepartement (int idEtudiant, int idDepart) ;
    Etudiant addAndAssignEtudiantToEquipeAndContract(Etudiant e, int idContrat, int idEquipe);
    List<Etudiant> getEtudiantsByDepartement (int idDepartement);
}
