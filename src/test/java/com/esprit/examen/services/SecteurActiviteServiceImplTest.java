package com.esprit.examen.services;


import com.esprit.examen.entities.SecteurActivite;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.junit.Assert.assertNull;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class SecteurActiviteServiceImplTest {
    @Autowired
    ISecteurActiviteService secteurActiviteSer;


    @Test
    public void testAddFournisseur(){
        List<SecteurActivite> secteurs = secteurActiviteSer.retrieveAllSecteurActivite();
        int size=secteurs.size();
        SecteurActivite s = new SecteurActivite("secteur1","ariana");
        SecteurActivite savedSecteur= secteurActiviteSer.addSecteurActivite(s);
        assertEquals(size+1, secteurActiviteSer.retrieveAllSecteurActivite().size());
        assertNotNull(savedSecteur.getLibelleSecteurActivite());
        secteurActiviteSer.deleteSecteurActivite(savedSecteur.getIdSecteurActivite());
    }


    @Test
    public void testRetrieveAllSecteurs() {
        SecteurActivite s= new SecteurActivite("secteur2","ghazala");
        SecteurActivite savedSecteur= secteurActiviteSer.addSecteurActivite(s);
        List<SecteurActivite> secteurs = secteurActiviteSer.retrieveAllSecteurActivite();
        assertNotEquals(secteurs.size(),0);
        secteurActiviteSer.deleteSecteurActivite(savedSecteur.getIdSecteurActivite());
    }


    @Test
    public void testDeleteFournisseur() {
        SecteurActivite s= new SecteurActivite("secteur3","marsa");
        SecteurActivite savedSecteur= secteurActiviteSer.addSecteurActivite(s);
        secteurActiviteSer.deleteSecteurActivite(savedSecteur.getIdSecteurActivite());
        assertNull(secteurActiviteSer.retrieveSecteurActivite(savedSecteur.getIdSecteurActivite()));
    }

    @Test
    public void testUpdateFournisseur(){
        SecteurActivite s= new SecteurActivite("secteur4","aouina");
        SecteurActivite savedSecteur= secteurActiviteSer.addSecteurActivite(s);
        SecteurActivite updatedSecteur = secteurActiviteSer.updateSecteurActivite(savedSecteur);
        assertEquals(Optional.ofNullable(updatedSecteur.getCodeSecteurActivite()),Optional.of(savedSecteur.getCodeSecteurActivite()));
        secteurActiviteSer.deleteSecteurActivite(updatedSecteur.getIdSecteurActivite());
    }
}
