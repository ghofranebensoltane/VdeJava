package com.example.FirstApplicationSpring.repositories;

import com.example.FirstApplicationSpring.model.Etudiant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface EtudiantRepository extends JpaRepository<Etudiant, Integer> {
    Optional<Etudiant> findByNomEtudiantAndPrenomEtudiant(String nomEtudiant, String prenomEtudiant);
    List<Etudiant> findByDepartementIdDepart(int idDepart);
}
