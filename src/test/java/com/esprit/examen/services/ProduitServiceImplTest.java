package com.esprit.examen.services;

import com.esprit.examen.entities.Produit;
import com.esprit.examen.repositories.ProduitRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;


@RunWith(SpringRunner.class)
@SpringBootTest
public class ProduitServiceImplTest {
    @Autowired
    IProduitService produitService;
    @MockBean
    private ProduitRepository PR;

    Produit p=new Produit(1L,"Ref N: 25A45ZDD6FF8","iPhone 14",5000,new Date(),new Date(),null,null,null);

    @Test
    public void retrieveAllProduits() {
        when(PR.findAll()).thenReturn(Stream
                .of(p)
                .collect(Collectors.toList()));
        assertEquals(1,produitService.retrieveAllProduits().size());
        System.out.println("Retrieve  All TEST :) ");
    }

    @Test
    public void testaddProduit() {

        when(PR.save(p)).thenReturn(p);
        assertNotNull(p);
        assertEquals(p, produitService.addProduit(p));
        System.out.println("Add TEST :D ");

    }

    @Test
    public void deleteProduit() {
        PR.save(p);
        produitService.deleteProduit(p.getIdProduit());
        verify(PR, times(1)).deleteById(p.getIdProduit());
        System.out.println("Delete TEST :( !");
    }

    @Test
    public void updateProduit() {
        when(PR.save(p)).thenReturn(p);
        assertNotNull(p);
        assertEquals(p, produitService.updateProduit(p));

        System.out.println("Update TEST :O !");
    }

    @Test
    public void retrieveProduit() {

        when(PR.findById(p.getIdProduit())).thenReturn(Optional.of(p));
        assertEquals(p, produitService.retrieveProduit(p.getIdProduit()));
        System.out.println("Retrieve P TEST :P !");
    }

    @Test
    public  void assignProduitToStock() {
    }
}