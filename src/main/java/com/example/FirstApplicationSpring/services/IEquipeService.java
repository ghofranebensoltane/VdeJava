package com.example.FirstApplicationSpring.services;

import com.example.FirstApplicationSpring.model.Equipe;

import java.util.List;

public interface IEquipeService {
    List<Equipe> getAllEquipes();
    Equipe  addEquipe(Equipe  e); // ajouter l’équipe avec son détail
    Equipe updateEquipe (Equipe  e);
    Equipe getOneEquipe (int idEquipe);
}
