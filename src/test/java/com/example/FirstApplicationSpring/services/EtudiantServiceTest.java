package com.example.FirstApplicationSpring.services;

import com.example.FirstApplicationSpring.model.Contrat;
import com.example.FirstApplicationSpring.model.Departement;
import com.example.FirstApplicationSpring.model.Equipe;
import com.example.FirstApplicationSpring.model.Etudiant;
import com.example.FirstApplicationSpring.repositories.ContratRepository;
import com.example.FirstApplicationSpring.repositories.DepartementRepository;
import com.example.FirstApplicationSpring.repositories.EquipeRepository;
import com.example.FirstApplicationSpring.repositories.EtudiantRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


class EtudiantServiceTest {

    @Mock
    private EtudiantRepository etudiantRepository;

    @Mock
    private DepartementRepository departementRepository;

    @Mock
    private ContratRepository contratRepository;

    @Mock
    private EquipeRepository equipeRepository;

    @InjectMocks
    private EtudiantService etudiantService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void getAllEtudiants() {
        // Arrange
        List<Etudiant> fakeList = List.of(new Etudiant(), new Etudiant());
        when(etudiantRepository.findAll()).thenReturn(fakeList);

        // Act
        List<Etudiant> result = etudiantService.getAllEtudiants();

        // Assert
        assertThat(result).hasSize(2);
        verify(etudiantRepository).findAll();
    }

    @Test
    void addEtudiant() {
        Etudiant etudiant = new Etudiant();
        when(etudiantRepository.save(etudiant)).thenReturn(etudiant);

        Etudiant result = etudiantService.addEtudiant(etudiant);

        assertThat(result).isEqualTo(etudiant);
        verify(etudiantRepository).save(etudiant);
    }

    @Test
    void updateEtudiant() {
        Etudiant etudiant = new Etudiant();
        etudiant.setNomEtudiant("Old Name");

        when(etudiantRepository.save(etudiant)).thenReturn(etudiant);

        Etudiant updated = etudiantService.updateEtudiant(etudiant);

        assertThat(updated.getNomEtudiant()).isEqualTo("Old Name");
        verify(etudiantRepository).save(etudiant);
    }

    @Test
    void getEtudiant() {
        Etudiant etudiant = new Etudiant();
        etudiant.setIdEtudiant(1);
        when(etudiantRepository.findById(1)).thenReturn(Optional.of(etudiant));

        Etudiant result = etudiantService.getEtudiant(1);

        assertThat(result).isEqualTo(etudiant);
    }

    @Test
    void removeEtudiant() {
        etudiantService.removeEtudiant(1);
        verify(etudiantRepository).deleteById(1);
    }

    @Test
    void assignEtudiantToDepartement() {
        Etudiant etudiant = new Etudiant();
        Departement departement = new Departement();

        when(etudiantRepository.findById(1)).thenReturn(Optional.of(etudiant));
        when(departementRepository.findById(10)).thenReturn(Optional.of(departement));
        when(etudiantRepository.save(etudiant)).thenReturn(etudiant);

        etudiantService.assignEtudiantToDepartement(1, 10);

        assertThat(etudiant.getDepartement()).isEqualTo(departement);
        verify(etudiantRepository).save(etudiant);
    }

    @Test
    void addAndAssignEtudiantToEquipeAndContract() {
        // Arrange
        Etudiant e = new Etudiant();
        Equipe equipe = new Equipe();
        equipe.setEtudiantSet(new HashSet<>());

        Contrat contrat = new Contrat();

        when(etudiantRepository.save(e)).thenReturn(e);
        when(contratRepository.findById(5)).thenReturn(Optional.of(contrat));
        when(equipeRepository.findById(9)).thenReturn(Optional.of(equipe));

        // Act
        Etudiant result = etudiantService.addAndAssignEtudiantToEquipeAndContract(e, 5, 9);

        // Assert
        assertThat(result.getEquipes()).contains(equipe);
        assertThat(contrat.getEtudiant()).isEqualTo(e);

        verify(etudiantRepository, times(2)).save(e); // une fois au début, une à la fin
        verify(equipeRepository).save(equipe);
        verify(contratRepository).save(contrat);
    }
    @Test
    void getEtudiantsByDepartement() {
    }
}