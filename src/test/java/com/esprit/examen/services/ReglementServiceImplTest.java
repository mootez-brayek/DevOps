package com.esprit.examen.services;

import static org.junit.Assert.*;
import com.esprit.examen.dto.ReglementDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
@AllArgsConstructor
public class ReglementServiceImplTest {
    private final IReglementService reglementService;


    @Test
    public void testAddReglement() {
        ReglementDto reglement = ReglementDto
                .builder()
                .dateReglement(new Date())
                .montantPaye(140.00F)
                .montantRestant(20F)
                .payee(true)
                .build();
        ReglementDto savedReglement = reglementService.addReglement(reglement);
        assertNotNull(savedReglement.getIdReglement());
        assertTrue(savedReglement.getPayee());
        assertSame(savedReglement.getMontantPaye(),140F);
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
}
