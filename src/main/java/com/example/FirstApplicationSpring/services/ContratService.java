package com.example.FirstApplicationSpring.services;

import com.example.FirstApplicationSpring.dto.ContratDTO;
import com.example.FirstApplicationSpring.model.Contrat;
import com.example.FirstApplicationSpring.model.Etudiant;
import com.example.FirstApplicationSpring.repositories.ContratRepository;
import com.example.FirstApplicationSpring.repositories.EtudiantRepository;
import io.swagger.v3.oas.models.info.Contact;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;


@Service
@RequiredArgsConstructor
public class ContratService implements IContratService{


    private final ContratRepository contratRepository;
    private final EtudiantRepository etudiantRepository;



    @Override
    @Scheduled(cron = "0 0 13 * * *")
    public String retrieveAndUpdateStatusContrat() {
        LocalDate today = LocalDate.now();
        Date todayDate = java.sql.Date.valueOf(today);
        LocalDate in15Days = today.plusDays(15);

        List<Contrat> contrats = contratRepository.findByDateFinContratBetween(today, in15Days);

        System.out.println("les contrats se terminent dans les 15 jours :");

        for (Contrat c : contrats) {
            System.out.println(" Contrat ID: " + c.getIdContrat());
            System.out.println(" Date fin : " + c.getDateFinContrat());
            System.out.println(" Spécialité : " + c.getSpecialite());
            System.out.println(" Étudiant  : " + c.getEtudiant().getPrenomEtudiant() + " " + c.getEtudiant().getNomEtudiant());

            if (c.getDateDebutContrat().before(todayDate) || c.getDateFinContrat().equals(todayDate)) {
                c.setArchive(true);
                contratRepository.save(c);
                System.out.println("   -> Archivé automatiquement.");
            }
        }
            return "Traitement terminé. Contrats affichés et mis à jour si nécessaire.";
        }


    @Override
    public ContratDTO affectContratToEtudiant(Contrat c, String nomEtudiant, String prenomEtudiant) {
        Etudiant etudiant = etudiantRepository.findByNomEtudiantAndPrenomEtudiant(nomEtudiant,prenomEtudiant)
                .orElseThrow(()-> new RuntimeException("etudiant introuvanble"));
        int countContratActif = contratRepository.countByEtudiantAndArchive(etudiant, false);

        if (countContratActif >= 5){
            throw new RuntimeException("l'etudiant a deja 5 contrat active");
        }
        c.setEtudiant(etudiant);
        c.setArchive(false);
        Contrat contrat = contratRepository.save(c);
        return ContratDTO.builder()
                .contrat(contrat)
                .nomEtudiant(nomEtudiant)
                .prenomEtudiant(prenomEtudiant)
                .build();
    }

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
