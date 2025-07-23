package com.example.FirstApplicationSpring.services;


import com.example.FirstApplicationSpring.model.Equipe;
import com.example.FirstApplicationSpring.repositories.EquipeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EquipeService implements IEquipeService {

    private final EquipeRepository equipeRepository;

    @Override
    public List<Equipe> getAllEquipes() {
        return equipeRepository.findAll();
    }

    @Override
    public Equipe addEquipe(Equipe e) {
        return equipeRepository.save(e);
    }

    @Override
    public Equipe updateEquipe(Equipe e) {
        return equipeRepository.save(e);
    }

    @Override
    public Equipe getOneEquipe(int idEquipe) {
        return equipeRepository.findById(idEquipe).orElse(null);
    }
}
