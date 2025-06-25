package com.example.FirstApplicationSpring.repositories;

import com.example.FirstApplicationSpring.model.Etudiant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface EtudiantRepository extends JpaRepository<Etudiant, Integer> {

}
