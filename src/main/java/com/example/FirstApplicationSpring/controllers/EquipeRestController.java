package com.example.FirstApplicationSpring.controllers;


import com.example.FirstApplicationSpring.model.Contrat;
import com.example.FirstApplicationSpring.model.Equipe;
import com.example.FirstApplicationSpring.services.IEquipeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/equipe")
public class EquipeRestController {

    private final IEquipeService iEquipeService;


    @GetMapping("/GetAllEquipe")
    public List<Equipe> getAllEquipe(){
        return iEquipeService.getAllEquipes();
    }

    @PostMapping("/addEquipe")
    public Equipe addEquipe(@RequestBody Equipe e){
        return iEquipeService.addEquipe(e);
    }
    @PutMapping("/updateEquipe")
    public Equipe updateEquipe(@RequestBody Equipe e){
        return iEquipeService.updateEquipe(e);
    }

    @GetMapping("/getOne/{idEquipe}")
    public Equipe getOneEquipe(@PathVariable int idEquipe){
        return iEquipeService.getOneEquipe(idEquipe);
    }

}
