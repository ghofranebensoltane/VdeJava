package com.example.FirstApplicationSpring.services;

import com.example.FirstApplicationSpring.model.Departement;
import com.example.FirstApplicationSpring.repositories.DepartementRepository;
import io.swagger.v3.oas.annotations.servers.Server;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class DepartementService implements IDepartementService{

    private final DepartementRepository departementRepository;


    @Override
    public List<Departement> getAllDepartements() {
        return departementRepository.findAll();
    }

    @Override
    public Departement addDepartement(Departement d) {
        return departementRepository.save(d);
    }

    @Override
    public Departement updateDepartement(Departement d) {
        return departementRepository.save(d);
    }

    @Override
    public Departement getOneDepartement(int idDepart) {
        return departementRepository.findById(idDepart).orElse(null);
    }
}
