package com.esprit.examen.dto;

import com.esprit.examen.entities.CategorieFournisseur;
import com.esprit.examen.entities.Fournisseur;
import com.esprit.examen.exceptions.FournisseurException;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;


@Builder
@Data
public class FournisseurDto {


    private long idFournisseur;

    private String code;

    private String libelle;

    private CategorieFournisseur categorieFournisseur;


    public static FournisseurDto fromEntity(Fournisseur fournisseur){
        if(fournisseur == null) throw  new FournisseurException("Empty");
        return FournisseurDto.builder()
                .idFournisseur(fournisseur.getIdFournisseur())
                .code(fournisseur.getCode())
                .libelle(fournisseur.getLibelle())
                .categorieFournisseur(fournisseur.getCategorieFournisseur())
                .build();
    }


    public static Fournisseur toEntity(FournisseurDto fournisseurDto){
        if(fournisseurDto == null) throw new FournisseurException(" is empty");
        return Fournisseur.builder()
                .idFournisseur(fournisseurDto.getIdFournisseur())
                .code(fournisseurDto.getCode())
                .libelle(fournisseurDto.getLibelle())
                .categorieFournisseur(fournisseurDto.getCategorieFournisseur())
                .build();
    }


    public static List<FournisseurDto> fromEntityList(List<Fournisseur> fournisseurs) {
        List<FournisseurDto> list = new ArrayList<>();
        fournisseurs.forEach(stock -> list.add(FournisseurDto.fromEntity(stock)));
        return list ;


    }


}
