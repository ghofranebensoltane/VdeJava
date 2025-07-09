package com.example.FirstApplicationSpring.repositories;

import com.example.FirstApplicationSpring.model.Contrat;
import com.example.FirstApplicationSpring.model.Etudiant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ContratRepository extends JpaRepository<Contrat, Integer> {
    int countByEtudiantAndArchive(Etudiant etudiant, boolean archive);
    List<Contrat> findByDateFinContratBetween(LocalDate start, LocalDate end);
}
