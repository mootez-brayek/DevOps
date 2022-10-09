package com.esprit.examen.services;

import com.esprit.examen.entities.CategorieFournisseur;
import com.esprit.examen.entities.DetailFournisseur;
import com.esprit.examen.entities.Fournisseur;
import com.esprit.examen.repositories.DetailFournisseurRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class FournisseurServiceImplTest {
    @Autowired
    IFournisseurService fournisseurSer;
    @Autowired
    DetailFournisseurRepository detailFournisseurRepository;


    @Test
    public void testAddFournisseur(){
        List<Fournisseur> fournisseurs = fournisseurSer.retrieveAllFournisseurs();
            int size=fournisseurs.size();
        Fournisseur f = new Fournisseur("fournisseur1","fournir", CategorieFournisseur.ORDINAIRE);
        Fournisseur savedFournisseur= fournisseurSer.addFournisseur(f);

        assertEquals(size+1, fournisseurSer.retrieveAllFournisseurs().size());
        assertNotNull(savedFournisseur.getLibelle());
        log.info("fournisseur has been added");
        fournisseurSer.deleteFournisseur(savedFournisseur.getIdFournisseur());
    }

    @Test
    public void testGetFournisseurs() {
        Fournisseur f= new Fournisseur("fournisseur2","abcd", CategorieFournisseur.CONVENTIONNE);
        Fournisseur savedFournisseur= fournisseurSer.addFournisseur(f);
        List<Fournisseur> fournisseursList = fournisseurSer.retrieveAllFournisseurs();
        assertNotEquals(fournisseursList.size(),0);
        fournisseurSer.deleteFournisseur(savedFournisseur.getIdFournisseur());
    }


    @Test
    public void testRemoveFournisseur() {
        Fournisseur f= new Fournisseur("fournisseur3","azdq", CategorieFournisseur.CONVENTIONNE);
        Fournisseur savedFournisseur= fournisseurSer.addFournisseur(f);
        fournisseurSer.deleteFournisseur(savedFournisseur.getIdFournisseur());
        assertNull(fournisseurSer.retrieveFournisseur(savedFournisseur.getIdFournisseur()));
    }

    @Test
    public void testModifyFournisseur(){
        Fournisseur f= new Fournisseur("fournisseur4","mmm", CategorieFournisseur.CONVENTIONNE);
        Fournisseur savedFournisseur= fournisseurSer.addFournisseur(f);
        Fournisseur updatedFournisseur = fournisseurSer.updateFournisseur(savedFournisseur);
        assertEquals(Optional.ofNullable(updatedFournisseur.getCode()),Optional.of(savedFournisseur.getCode()));
        fournisseurSer.deleteFournisseur(updatedFournisseur.getIdFournisseur());
    }


    @Test
    public void testSaveDetailFournisseur(){
        Fournisseur f= new Fournisseur("fournisseur5","aaa", CategorieFournisseur.CONVENTIONNE);
        DetailFournisseur d = new DetailFournisseur("fournisseur5@gmail.com","centre ville","abcd");
        Fournisseur savedFournisseur= fournisseurSer.addFournisseur(f);
        DetailFournisseur savedDetailsFournisseur= detailFournisseurRepository.save(d);
        f.setDetailFournisseur(d);
        assertNotNull(savedFournisseur.getDetailFournisseur());
        fournisseurSer.deleteFournisseur(savedFournisseur.getIdFournisseur());
        detailFournisseurRepository.delete(savedDetailsFournisseur);
    }

}
