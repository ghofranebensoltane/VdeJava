package com.example.FirstApplicationSpring.controllers;

import com.example.FirstApplicationSpring.config.security.JwtFilter;
import com.example.FirstApplicationSpring.config.security.JwtUtil;
import com.example.FirstApplicationSpring.model.Etudiant;
import com.example.FirstApplicationSpring.services.IEtudiantService;
import com.example.FirstApplicationSpring.services.TokenBlacklistService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(
        controllers = EtudiantRestController.class,
        excludeFilters = @ComponentScan.Filter(
                type = FilterType.ASSIGNABLE_TYPE,
                classes = JwtFilter.class
        )
)
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
class EtudiantRestControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private IEtudiantService etudiantService;
    @MockitoBean
    private TokenBlacklistService tokenBlacklistService;
    @MockitoBean
    private JwtUtil jwtUtil;





    @Test
    @WithMockUser(username = "user", roles = {"USER"})
    void getAllEtudiants() throws Exception {
        Etudiant etudiant = Etudiant.builder()
                .idEtudiant(1)
                .nomEtudiant("Ali")
                .prenomEtudiant("Ben Ali")
                .build();
        List<Etudiant> etudiants = List.of(etudiant);
        Mockito.when(etudiantService.getAllEtudiants()).thenReturn(etudiants);

        mockMvc.perform(get("/api/etudiants/GetALL"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].nomEtudiant").value("Ali"));
    }

  @Test
  @WithMockUser(username = "admin", roles = {"ADMIN"})
    void addEtudiant() throws Exception {
     Etudiant etudiant = Etudiant.builder()
            .idEtudiant(1)
            .nomEtudiant("Ali")
            .prenomEtudiant("Ben Ali")
            .build();
    Mockito.when(etudiantService.addEtudiant(Mockito.any(Etudiant.class)))
            .thenReturn(etudiant);

    mockMvc.perform(post("/api/etudiants/add")
                    .with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(etudiant)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.idEtudiant").value(1))
            .andExpect(jsonPath("$.nomEtudiant").value("Ali"))
            .andExpect(jsonPath("$.prenomEtudiant").value("Ben Ali"));
    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    void updateEtudiant() throws Exception {
    Etudiant etudiant = Etudiant.builder()
            .idEtudiant(1)
            .nomEtudiant("Ali")
            .prenomEtudiant("Ben Ali")
            .build();

    Mockito.when(etudiantService.updateEtudiant(Mockito.any(Etudiant.class)))
            .thenReturn(etudiant);

    mockMvc.perform(put("/api/etudiants/update")
            .with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(etudiant)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.idEtudiant").value(1))
            .andExpect(jsonPath("$.nomEtudiant").value("Ali"))
            .andExpect(jsonPath("$.prenomEtudiant").value("Ben Ali"));
    }

   @Test
   @WithMockUser(username = "user", roles = {"USER"})
    void getEtudiant() throws Exception {
       Etudiant etudiant = Etudiant.builder()
               .idEtudiant(1)
               .nomEtudiant("Ali")
               .prenomEtudiant("Ben Ali")
               .build();

       Mockito.when(etudiantService.getEtudiant(1)).thenReturn(etudiant);

       mockMvc.perform(get("/api/etudiants/getOne/1"))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.idEtudiant").value(1))
               .andExpect(jsonPath("$.nomEtudiant").value("Ali"))
               .andExpect(jsonPath("$.prenomEtudiant").value("Ben Ali"));
   }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    void removeEtudiant() throws Exception {
        Mockito.doNothing().when(etudiantService).removeEtudiant(1);

        mockMvc.perform(delete("/api/etudiants/delete/1")
                .with(csrf()))
                .andExpect(status().isOk());
        Mockito.verify(etudiantService, Mockito.times(1)).removeEtudiant(1);
    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
void assignEtudiantToDepartement_combined() throws Exception {
    //  succes
    Mockito.doNothing().when(etudiantService).assignEtudiantToDepartement(1, 2);

    mockMvc.perform(put("/api/etudiants/etudiant/1/departement/2")
            .with(csrf()))
            .andExpect(status().isOk())
            .andExpect(content().string("Étudiant assigné au département avec succès."));

    // echoue
    Mockito.doThrow(new RuntimeException("Département inconnu")).when(etudiantService).assignEtudiantToDepartement(1, 999);

    mockMvc.perform(put("/api/etudiants/etudiant/1/departement/999")
            .with(csrf()))
            .andExpect(status().isOk())
            .andExpect(content().string("Erreur : Département inconnu"));
}

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
void testAssignEtudiantToDepartement() throws Exception {
    Etudiant etudiant = Etudiant.builder()
            .idEtudiant(1)
            .nomEtudiant("Ali")
            .prenomEtudiant("Ben Ali")
            .build();

    Mockito.when(etudiantService.addAndAssignEtudiantToEquipeAndContract(
            Mockito.any(Etudiant.class), Mockito.eq(10), Mockito.eq(20)))
        .thenReturn(etudiant);

    mockMvc.perform(post("/api/etudiants/addAndAssign/10/20")
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(etudiant)))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.idEtudiant").value(1))
                    .andExpect(jsonPath("$.nomEtudiant").value("Ali"))
                    .andExpect(jsonPath("$.prenomEtudiant").value("Ben Ali"));
    }
}