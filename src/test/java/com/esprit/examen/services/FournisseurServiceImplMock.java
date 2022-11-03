package com.esprit.examen.services;

import com.esprit.examen.entities.CategorieFournisseur;
import com.esprit.examen.entities.Fournisseur;
import com.esprit.examen.repositories.FournisseurRepository;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class FournisseurServiceImplMock {
    @Mock
    FournisseurRepository fournisseurRepo;

    @InjectMocks
    FournisseurServiceImpl fournisseurSer;

    Fournisseur fournisseur=new Fournisseur((long)1,"fournisseur1","fournir1",CategorieFournisseur.ORDINAIRE);
    List<Fournisseur> listFournisseur= new ArrayList<>(){
        {
            add(new Fournisseur((long)2,"fournisseur2","fournir2",CategorieFournisseur.CONVENTIONNE));
            add(new Fournisseur((long)3,"fournisseur3","fournir3",CategorieFournisseur.CONVENTIONNE));
        }

    };
    @Test
    public void testRetrieveFournissuer(){
        Mockito.when(fournisseurRepo.findById(Mockito.anyLong())).thenReturn(Optional.of(fournisseur));
        Fournisseur fournisseur1 = fournisseurSer.retrieveFournisseur((long)2);
        Assertions.assertNotNull(fournisseur1);
    }
}
