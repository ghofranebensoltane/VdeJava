package com.example.FirstApplicationSpring.controllers;


import com.example.FirstApplicationSpring.dto.ContratDTO;
import com.example.FirstApplicationSpring.model.Contrat;
import com.example.FirstApplicationSpring.services.IContratService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/contrat")
public class ContratRestController {
    private final IContratService iContratService;


    @GetMapping("/GetAllContrat")
    public List<Contrat> getAllContrat(){
        return iContratService.getAllContrats();
    }

    @PostMapping("/addContrat")
    public Contrat addContrat(@RequestBody Contrat c){
        return iContratService.addContrat(c);
    }
    @PutMapping("/updateContrat")
    public Contrat updateContrat(@RequestBody Contrat c){
        return iContratService.updateContrat(c);
    }

    @GetMapping("/getOne/{idContrat}")
    public Contrat getOneContrat(@PathVariable int idContrat){
        return iContratService.getOneContrat(idContrat);
    }

    @DeleteMapping("/delete/{idContrat}")
    public void removeContrat(@PathVariable int idContrat){
        iContratService.removeContrat(idContrat); ;
    }
    @PostMapping("/affecter")
    public ContratDTO affectContratToEtudiant(@RequestBody Contrat c,@RequestParam String nomEtudiant ,@RequestParam String prenomEtudiant){
       return iContratService.affectContratToEtudiant(c,nomEtudiant,prenomEtudiant);
    }
    @GetMapping("/check-contrats")
    public String checkAndUpdateContratsExpirants() {
         return iContratService.retrieveAndUpdateStatusContrat();
    }
}
