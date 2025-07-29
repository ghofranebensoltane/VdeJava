package com.example.FirstApplicationSpring.repositories;

import com.example.FirstApplicationSpring.model.Etudiant;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
@ActiveProfiles("test")
class EtudiantRepositoryTest {

    @Autowired EtudiantRepository etudiantRepository;

    @Test
    void should_save_etudiant() {
        Etudiant etudiant = new Etudiant();
        etudiant.setNomEtudiant("Sami");
        etudiant.setPrenomEtudiant("Ali");

        Etudiant saved = etudiantRepository.save(etudiant);

        assertThat(saved.getIdEtudiant()).isNotZero();
        assertThat(saved.getNomEtudiant()).isEqualTo("Sami");
    }

    @Test
    void should_find_etudiant_by_id() {
        Etudiant etudiant = new Etudiant();
        etudiant.setNomEtudiant("Yasmine");
        etudiant.setPrenomEtudiant("Leila");
        Etudiant saved = etudiantRepository.save(etudiant);

        Optional<Etudiant> result = etudiantRepository.findById(saved.getIdEtudiant());

        assertThat(result).isPresent();
        assertThat(result.get().getNomEtudiant()).isEqualTo("Yasmine");
    }

    @Test
    void should_return_all_etudiants() {
        Etudiant e1 = new Etudiant();
        e1.setNomEtudiant("Ali");
        e1.setPrenomEtudiant("Karim");

        Etudiant e2 = new Etudiant();
        e2.setNomEtudiant("Sarra");
        e2.setPrenomEtudiant("Lina");

        etudiantRepository.saveAll(List.of(e1, e2));

        List<Etudiant> results = etudiantRepository.findAll();

        assertThat(results).hasSizeGreaterThanOrEqualTo(2);
    }

    @Test
    void should_update_etudiant() {
        Etudiant etudiant = new Etudiant();
        etudiant.setNomEtudiant("Ahmed");
        etudiant.setPrenomEtudiant("Initial");
        Etudiant saved = etudiantRepository.save(etudiant);

        saved.setPrenomEtudiant("Modifié");
        Etudiant updated = etudiantRepository.save(saved);

        assertThat(updated.getPrenomEtudiant()).isEqualTo("Modifié");
    }

    @Test
    void should_delete_etudiant() {
        Etudiant etudiant = new Etudiant();
        etudiant.setNomEtudiant("Nour");
        etudiant.setPrenomEtudiant("Zina");

        Etudiant saved = etudiantRepository.save(etudiant);
        int id = saved.getIdEtudiant();

        etudiantRepository.deleteById(id);

        Optional<Etudiant> result = etudiantRepository.findById(id);
        assertThat(result).isEmpty();
    }





    @Test
    void findByNomEtudiantAndPrenomEtudiant() {
        Etudiant etudiant = new Etudiant();
        etudiant.setNomEtudiant("ghof");
        etudiant.setPrenomEtudiant("ben");


        etudiantRepository.save(etudiant);

        // Act
        Optional<Etudiant> result = etudiantRepository
                .findByNomEtudiantAndPrenomEtudiant("ghof", "ben");

        // Assert
        assertThat(result).isPresent();
        assertThat(result.get().getNomEtudiant()).isEqualTo("ghof");
        assertThat(result.get().getPrenomEtudiant()).isEqualTo("ben");
    }



    @Test
    void findByDepartementIdDepart() {
        // Arrange
        Etudiant e1 = new Etudiant();
        e1.setNomEtudiant("Ali");
        e1.setPrenomEtudiant("Karim");

        Etudiant e2 = new Etudiant();
        e2.setNomEtudiant("Zahra");
        e2.setPrenomEtudiant("Nadia");


        Etudiant e3 = new Etudiant();
        e3.setNomEtudiant("Jean");
        e3.setPrenomEtudiant("Paul");

        etudiantRepository.saveAll(List.of(e1, e2, e3));

        // Act
        List<Etudiant> results = etudiantRepository.findByDepartementIdDepart(2);

        // Assert
        assertThat(results).hasSize(2);
        assertThat(results).extracting(Etudiant::getNomEtudiant)
                .containsExactlyInAnyOrder("Ali", "Zahra");
    }
}
