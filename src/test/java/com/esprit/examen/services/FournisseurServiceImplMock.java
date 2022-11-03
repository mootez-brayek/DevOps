package com.esprit.examen.services;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class FournisseurServiceImplMock {
   /* @Mock
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
    }*/
}
