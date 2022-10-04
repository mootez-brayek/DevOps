package com.esprit.examen.services;

import static org.junit.Assert.*;
import com.esprit.examen.dto.ReglementDto;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.Date;
import java.util.List;
import java.util.Optional;


@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j

public class ReglementServiceImplTest {
    @Autowired
    IReglementService reglementService;
    @Autowired
    IFactureService factureService;


    @Test
    public void testAddReglement() {
        ReglementDto reglement = ReglementDto
                .builder()
                .dateReglement(new Date())
                .montantPaye(140.0F)
                .montantRestant(20F)
                .payee(true)
                .build();
        ReglementDto savedReglement = reglementService.addReglement(reglement);
        assertNotNull(savedReglement.getIdReglement());
        assertTrue(savedReglement.getPayee());
        assertEquals(Optional.ofNullable(savedReglement.getMontantPaye()),Optional.of(140F));
        reglementService.deleteReglement(savedReglement);

    }

    @Test
    public void testDeleteReglement() {
        ReglementDto reglement = ReglementDto
                .builder()
                .dateReglement(new Date())
                .montantPaye(140.00F)
                .montantRestant(20F)
                .payee(true)
                .build();
        ReglementDto savedReglement= reglementService.addReglement(reglement);
        reglementService.deleteReglement(savedReglement);
        assertNull(reglementService.retrieveReglement(savedReglement.getIdReglement()));
    }

    @Test
    public void TestUpdateReglement(){
        ReglementDto reglement = ReglementDto
                .builder()
                .dateReglement(new Date())
                .montantPaye(140.00F)
                .montantRestant(20F)
                .payee(true)
                .build();
        //add new reglement
        ReglementDto savedReglement = reglementService.addReglement(reglement);
        savedReglement.setMontantPaye(160F);
        // update a field in a reglement service
        ReglementDto updatedReglement = reglementService.updateReglement(savedReglement);
        // Assert if the new updated field is the same as expected !
        assertEquals(Optional.ofNullable(updatedReglement.getMontantPaye()),Optional.of(savedReglement.getMontantPaye()));
        // if every thing goes as expected then we delete the mock reglement added for test
        reglementService.deleteReglement(updatedReglement);


    }
    @Test
    public void TestRetrieveAllReglements(){
        //call retrieve all method
        List<ReglementDto> reglementsList = reglementService.retrieveAllReglements();
        // test if the list returned from the retrieve all method is not empty
        assertNotEquals(reglementsList.size(),0);

    }
}
