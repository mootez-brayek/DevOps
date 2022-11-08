package com.esprit.examen.services;

import com.esprit.examen.entities.SecteurActivite;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class SecteurActiviteServiceImplTest {
 @Autowired
    ISecteurActiviteService secteurActiviteService ;
    @Test
    public void testAddFournisseur(){
        List<SecteurActivite> secteurs = secteurActiviteService.retrieveAllSecteurActivite();
        int size =secteurs.size();
        SecteurActivite f = new SecteurActivite("secteur1","secteur");
        SecteurActivite savedsecteur= secteurActiviteService.addSecteurActivite(f);
        assertEquals(size+1, secteurActiviteService.retrieveAllSecteurActivite().size());
        assertNotNull(savedsecteur.getLibelleSecteurActivite());
        log.info("SecteurActivite has been added");
        secteurActiviteService.deleteSecteurActivite(savedsecteur.getIdSecteurActivite());
        log.info("SecteurActivite has been deleted");
    }
    @Test
    public void testGetFournisseurs() {
        List<SecteurActivite> secteurs = secteurActiviteService.retrieveAllSecteurActivite();
        int size =secteurs.size();
        SecteurActivite f = new SecteurActivite("secteur1","secteur");
        SecteurActivite f1 = new SecteurActivite("secteur2","secteur2");
        SecteurActivite savedsecteur= secteurActiviteService.addSecteurActivite(f);
        SecteurActivite savedsecteur1= secteurActiviteService.addSecteurActivite(f1);
        assertEquals(size+2, secteurActiviteService.retrieveAllSecteurActivite().size());
        assertNotNull(savedsecteur.getLibelleSecteurActivite());
        log.info("secteur has been added");
        secteurActiviteService.deleteSecteurActivite(savedsecteur.getIdSecteurActivite());
        secteurActiviteService.deleteSecteurActivite(savedsecteur1.getIdSecteurActivite());
    }
}
