package com.example.FirstApplicationSpring.controllers;


import com.example.FirstApplicationSpring.model.Etudiant;
import com.example.FirstApplicationSpring.services.IEtudiantService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/etudiants")
@SecurityRequirement(name = "bearerAuth")
public class EtudiantRestController {

    private final IEtudiantService iEtudiantService;

    @GetMapping("/GetALL")
    public List<Etudiant> getAllEtudiants(){
        return iEtudiantService.getAllEtudiants();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/add")
    public Etudiant addEtudiant(@RequestBody Etudiant e){
        return iEtudiantService.addEtudiant(e);
    }

    @PutMapping("/update")
    public Etudiant updateEtudiant(@RequestBody Etudiant e){
        return iEtudiantService.updateEtudiant(e);
    }

   @PreAuthorize("hasRole('USER')")
    @GetMapping("/getOne/{idEtudiant}")
    public Etudiant getEtudiant(@PathVariable int idEtudiant){
        return iEtudiantService.getEtudiant(idEtudiant);
    }

    @DeleteMapping("/delete/{idEtudiant}")
    public void removeEtudiant(@PathVariable int idEtudiant){
        iEtudiantService.removeEtudiant(idEtudiant);
    }

   @PutMapping("/etudiant/{idEtudiant}/departement/{idDepart}")
    public String assignEtudiantToDepartement(@PathVariable int idEtudiant,@PathVariable int idDepart){
      try{  iEtudiantService.assignEtudiantToDepartement(idEtudiant,idDepart);
          return "Étudiant assigné au département avec succès.";
      } catch (RuntimeException e) {
          return "Erreur : " + e.getMessage();

      }
   }
   @PostMapping("/addAndAssign/{idContrat}/{idEquipe}")
   public Etudiant assignEtudiantToDepartement(@RequestBody Etudiant etudiant,@PathVariable int idContrat,@PathVariable int idEquipe ){

    return iEtudiantService.addAndAssignEtudiantToEquipeAndContract(etudiant,idContrat,idEquipe);

   }

    @GetMapping("/by-departement/{idDepartement}")
    public List<Etudiant> getEtudiantsByDepartement(@RequestParam int idDepart) {
      return  iEtudiantService.getEtudiantsByDepartement(idDepart);
    }

}
