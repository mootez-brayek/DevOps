package com.esprit.examen.services;

import com.esprit.examen.dto.ReglementDto;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ReglementServiceImplMockTest {

    ReglementServiceImpl reglementService = mock(ReglementServiceImpl.class);
    ReglementDto reglement = ReglementDto
            .builder()
            .dateReglement(new Date())
            .idReglement(1L)
            .payee(false)
            .montantRestant(120F)
            .montantPaye(100F)
            .build();

    ReglementDto reglement2 = ReglementDto
            .builder()
            .dateReglement(new Date())
            .idReglement(2L)
            .payee(true)
            .montantRestant(100F)
            .montantPaye(100F)
            .build();


    ReglementDto reglement3 = ReglementDto
            .builder()
            .dateReglement(new Date())
            .idReglement(3L)
            .payee(false)
            .montantRestant(250F)
            .montantPaye(200F)
            .build();


    List<ReglementDto> list = new ArrayList<ReglementDto>() {
        {
            add(reglement2);
            add(reglement3);
        }
    };


    @Test
    public void testAddReglement() {

        when(reglementService.addReglement(reglement)).thenReturn(reglement);

        Assert.assertNotNull(reglementService.addReglement(reglement).getIdReglement());

        verify(reglementService).addReglement(reglement);

    }


    @Test
    public void testRetreiveAllReglements() {

        when(reglementService.retrieveAllReglements()).thenReturn(list);
        assertNotEquals(list.size(), 0);

    }


    @Test
    public void testretRieveReglement() {

        when(reglementService.retrieveReglement(reglement.getIdReglement())).thenReturn(reglement);
        assertEquals(reglement, reglementService.retrieveReglement(reglement.getIdReglement()));
        verify(reglementService).retrieveReglement(reglement.getIdReglement());
    }


    @Test
    public void testUpdateReglement(){

        when(reglementService.updateReglement(reglement)).thenReturn(reglement);
        assertNotEquals(Optional.ofNullable(reglement.getMontantPaye()),Optional.of(reglementService.updateReglement(reglement)));
        verify(reglementService).updateReglement(reglement);


    }
}
