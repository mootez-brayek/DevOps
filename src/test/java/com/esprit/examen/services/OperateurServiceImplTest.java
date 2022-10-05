package com.esprit.examen.services;

import com.esprit.examen.dto.OperateurDto;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j

public class OperateurServiceImplTest {
    @Autowired
    IOperateurService operateurService;
    @Test
    public void testAddOperateur() {
        OperateurDto operateur = OperateurDto
                .builder()
                .nom("ines")
                .prenom("nfougui")
                .password("test")
                .build();
        OperateurDto savedOperateur = operateurService.addOperateur(operateur);
        assertNotNull(savedOperateur.getIdOperateur());
        assertEquals(Optional.ofNullable(savedOperateur.getPassword()),Optional.of("test"));
        operateurService.deleteOperateur(savedOperateur.getIdOperateur());
    }
    @Test
    public void testRetrieveOperateur() {
        OperateurDto operateur = OperateurDto
                .builder()
                .nom("ines")
                .prenom("nfougui")
                .password("test")
                .build();
        OperateurDto savedOperateur = operateurService.addOperateur(operateur);
        assertEquals(savedOperateur,operateurService.retrieveOperateur(savedOperateur.getIdOperateur()));
        operateurService.deleteOperateur(savedOperateur.getIdOperateur());
    }
    @Test
    public void testDeleteOperateur() {
        OperateurDto operateur = OperateurDto
                .builder()
                .nom("ines")
                .prenom("nfougui")
                .password("test")
                .build();
        OperateurDto savedOperateur= operateurService.addOperateur(operateur);
        operateurService.deleteOperateur(savedOperateur.getIdOperateur());
        assertNull(operateurService.retrieveOperateur(savedOperateur.getIdOperateur()));
    }

    @Test
    public void TestUpdateOperateur(){
        OperateurDto operateur = OperateurDto
                .builder()
                .nom("ines")
                .prenom("nfougui")
                .password("test")
                .build();
        OperateurDto savedOperateur = operateurService.addOperateur(operateur);
        savedOperateur.setPassword("test2");
        OperateurDto updateOperateur = operateurService.updateOperateur(savedOperateur);
        assertEquals(Optional.ofNullable(updateOperateur.getPassword()),Optional.of(savedOperateur.getPassword()));
        operateurService.deleteOperateur(updateOperateur.getIdOperateur());
    }
    @Test
    public void TestRetrieveAllOperateur() {
        OperateurDto operateur = OperateurDto
                .builder()
                .nom("ines")
                .prenom("nfougui")
                .password("test")
                .build();
        log.info("add new test operateur");
        OperateurDto savedOperateur = operateurService.addOperateur(operateur);
        log.info("retrieve all operateurs");
        List<OperateurDto> OperateurList = operateurService.retrieveAllOperateurs();
        log.info("assert that operateur list is not empty");
        assertNotEquals(OperateurList.size(), 0);
        log.info("delete the test operateur");
        operateurService.deleteOperateur(savedOperateur.getIdOperateur());
    }
}
