package com.example.FirstApplicationSpring.services;

import com.example.FirstApplicationSpring.model.Contrat;
import com.example.FirstApplicationSpring.repositories.ContratRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class ContratService implements IContratService{

    private final ContratRepository contratRepository;

    @Override
    public List<Contrat> getAllContrats() {
        return contratRepository.findAll();
    }

    @Override
    public Contrat updateContrat(Contrat c) {
        return contratRepository.save(c);
    }

    @Override
    public Contrat addContrat(Contrat c) {
        return contratRepository.save(c);
    }

    @Override
    public Contrat getOneContrat(int idContrat) {
        return contratRepository.findById(idContrat).orElse(null);
    }

    @Override
    public void removeContrat(int idContrat) {
        contratRepository.deleteById(idContrat);

    }
}
