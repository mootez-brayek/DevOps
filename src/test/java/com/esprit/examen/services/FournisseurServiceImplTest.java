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
            int size =fournisseurs.size();
        log.info("initialize size of the list");
        Fournisseur f = new Fournisseur("fournisseur1","fournir", CategorieFournisseur.ORDINAIRE);
        Fournisseur savedFournisseur= fournisseurSer.addFournisseur(f);
        log.info("ajouter un fournisseur");
        assertEquals(size+1, fournisseurSer.retrieveAllFournisseurs().size());
        log.info("if size=size+1 alors test accepte");
        assertNotNull(savedFournisseur.getLibelle());
        fournisseurSer.deleteFournisseur(savedFournisseur.getIdFournisseur());
        log.info("supprimer le fournisseur");
    }
    @Test
    public void testGetFournisseurs() {
        List<Fournisseur> fournisseurs = fournisseurSer.retrieveAllFournisseurs();
        int size =fournisseurs.size();
        log.info("initialize size of the list");
        Fournisseur f = new Fournisseur("fournisseur1","fournir", CategorieFournisseur.ORDINAIRE);
        Fournisseur o = new Fournisseur("fournisseur2","fournir2", CategorieFournisseur.CONVENTIONNE);
        Fournisseur savedFournisseur= fournisseurSer.addFournisseur(f);
        log.info("ajouter un fournisseur 1");
        Fournisseur savedFournisseur2= fournisseurSer.addFournisseur(o);
        log.info("ajouter un fournisseur 2");
        assertEquals(size+2, fournisseurSer.retrieveAllFournisseurs().size());
        log.info("if size=size+2 alors test accepte");
        assertNotNull(savedFournisseur.getLibelle());
        fournisseurSer.deleteFournisseur(savedFournisseur.getIdFournisseur());
        fournisseurSer.deleteFournisseur(savedFournisseur2.getIdFournisseur());
        log.info("supprimer les fournisseurs");
    }


    @Test
    public void testRemoveFournisseur() {
        Fournisseur f= new Fournisseur("fournisseur3","azdq", CategorieFournisseur.CONVENTIONNE);
        Fournisseur savedFournisseur= fournisseurSer.addFournisseur(f);
        log.info("ajouter un fournisseur");
        fournisseurSer.deleteFournisseur(savedFournisseur.getIdFournisseur());
        log.info("supprimer le fournisseur");
        assertNull(fournisseurSer.retrieveFournisseur(savedFournisseur.getIdFournisseur()));

    }

    @Test
    public void testModifyFournisseur(){
        Fournisseur f= new Fournisseur("fournisseur4","mmm", CategorieFournisseur.CONVENTIONNE);
        Fournisseur savedFournisseur= fournisseurSer.addFournisseur(f);
        log.info("ajouter le fournisseur");
        Fournisseur updatedFournisseur = fournisseurSer.updateFournisseur(savedFournisseur);
        log.info("update le fournisseur");
        assertEquals(Optional.ofNullable(updatedFournisseur.getCode()),Optional.of(savedFournisseur.getCode()));
        fournisseurSer.deleteFournisseur(updatedFournisseur.getIdFournisseur());
        log.info("supprimer le fournisseur");
    }


    @Test
    public void testSaveDetailFournisseur(){
        Fournisseur f= new Fournisseur("fournisseur5","aaa", CategorieFournisseur.CONVENTIONNE);
        DetailFournisseur d = new DetailFournisseur("fournisseur5@gmail.com","centre ville","abcd");
        Fournisseur savedFournisseur= fournisseurSer.addFournisseur(f);
        log.info("ajouter le fournisseur");
        DetailFournisseur savedDetailsFournisseur= detailFournisseurRepository.save(d);
        log.info("ajouter details");
        f.setDetailFournisseur(d);
        log.info("ajouter details a fournisseur");
        assertNotNull(savedFournisseur.getDetailFournisseur());
        fournisseurSer.deleteFournisseur(savedFournisseur.getIdFournisseur());
        detailFournisseurRepository.delete(savedDetailsFournisseur);
        log.info("suprimer fournisseur");
    }

}