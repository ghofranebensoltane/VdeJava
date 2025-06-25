package com.example.FirstApplicationSpring.repositories;

import com.example.FirstApplicationSpring.model.Departement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface DepartementRepository extends JpaRepository<Departement, Integer> {
}
