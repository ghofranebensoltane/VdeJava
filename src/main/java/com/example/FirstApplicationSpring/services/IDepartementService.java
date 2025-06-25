package com.example.FirstApplicationSpring.services;

import com.example.FirstApplicationSpring.model.Departement;

import java.util.List;

public interface IDepartementService {
    List<Departement> getAllDepartements();
    Departement addDepartement (Departement d);
    Departement updateDepartement (Departement d);
    Departement getOneDepartement (int idDepart);
}
