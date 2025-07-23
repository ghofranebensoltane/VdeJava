package com.example.FirstApplicationSpring.controllers;


import com.example.FirstApplicationSpring.model.Contrat;
import com.example.FirstApplicationSpring.model.Departement;
import com.example.FirstApplicationSpring.services.IDepartementService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/departement")
public class DepartementRestController {
    private final IDepartementService iDepartementService;


    @GetMapping("/GetAllDepartement")
    public List<Departement> getAllDepartement(){
        return iDepartementService.getAllDepartements();
    }

    @PostMapping("/addDepartement")
    public Departement addDepartement(@RequestBody Departement d){
        return iDepartementService.addDepartement(d);
    }
    @PutMapping("/updateDepartement")
    public Departement updateDepartement(@RequestBody Departement d){
        return iDepartementService.updateDepartement(d);
    }

    @GetMapping("/getOne/{idDepart}")
    public Departement getOneDepartement(@PathVariable int idDepart){
        return iDepartementService.getOneDepartement(idDepart);
    }

}
