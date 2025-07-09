package com.example.FirstApplicationSpring.services;

import com.example.FirstApplicationSpring.model.Contrat;
import com.example.FirstApplicationSpring.model.Departement;
import com.example.FirstApplicationSpring.model.Equipe;
import com.example.FirstApplicationSpring.model.Etudiant;
import com.example.FirstApplicationSpring.repositories.ContratRepository;
import com.example.FirstApplicationSpring.repositories.DepartementRepository;
import com.example.FirstApplicationSpring.repositories.EquipeRepository;
import com.example.FirstApplicationSpring.repositories.EtudiantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class EtudiantService implements IEtudiantService{

   private final EtudiantRepository etudiantRepository;
   private final DepartementRepository departementRepository;
   private final ContratRepository contratRepository;
   private final EquipeRepository equipeRepository;

    @Override
    public List<Etudiant> getAllEtudiants() {
        return etudiantRepository.findAll();
    }

    @Override
    public Etudiant addEtudiant(Etudiant e) {
        return etudiantRepository.save(e);
    }

    @Override
    public Etudiant updateEtudiant(Etudiant e) {
        return etudiantRepository.save(e);
    }

    @Override
    public Etudiant getEtudiant(int idEtudiant) {
        return etudiantRepository.findById(idEtudiant).orElse(null);
    }

    @Override
    public void removeEtudiant(int idEtudiant) {
        etudiantRepository.deleteById(idEtudiant);
    }

    @Override
    public void assignEtudiantToDepartement(int idEtudiant, int idDepart) {
        Etudiant etudiant = etudiantRepository.findById(idEtudiant).orElse(null);
        Departement departement = departementRepository.findById( idDepart).orElse(null);
        etudiant.setDepartement(departement);
        etudiantRepository.save(etudiant);
    }

    @Override
    public Etudiant addAndAssignEtudiantToEquipeAndContract(Etudiant e, int idContrat, int idEquipe) {
        Etudiant nouveauEtudiant = etudiantRepository.save(e);
        Contrat contrat = contratRepository.findById(idContrat).orElse(null);
        Equipe equipe = equipeRepository.findById(idEquipe).orElse(null);

        contrat.setEtudiant(nouveauEtudiant);
        contratRepository.save(contrat);

        Set<Etudiant> etudiantsEquipe = equipe.getEtudiantSet();
        if (etudiantsEquipe == null) {
            etudiantsEquipe = new HashSet<>();
        }
        etudiantsEquipe.add(nouveauEtudiant);
        equipe.setEtudiantSet(etudiantsEquipe);


        Set<Equipe> equipesEtudiant = nouveauEtudiant.getEquipes();
        if (equipesEtudiant == null) {
            equipesEtudiant = new HashSet<>();
        }
        equipesEtudiant.add(equipe);
        nouveauEtudiant.setEquipes(equipesEtudiant);
        equipeRepository.save(equipe);
        etudiantRepository.save(nouveauEtudiant);
        return nouveauEtudiant;

    }

    @Override
    public List<Etudiant> getEtudiantsByDepartement(int idDepartement) {
        return List.of();
    }


}
