package com.esprit.examen.dto;


import com.esprit.examen.entities.DetailFournisseur;
import com.esprit.examen.exceptions.FournisseurException;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Builder
@Data
public class DetailFournisseurDto {

    private Long idDetailFournisseur;

    private String email;

    private Date dateDebutCollaboration;

    private String adresse;

    private String matricule;


    public static DetailFournisseurDto fromEntity(DetailFournisseur detailFournisseur){
        if(detailFournisseur == null) throw  new FournisseurException("Empty");
        return DetailFournisseurDto.builder()
                .idDetailFournisseur(detailFournisseur.getIdDetailFournisseur())
                .email(detailFournisseur.getEmail())
                .dateDebutCollaboration(detailFournisseur.getDateDebutCollaboration())
                .adresse(detailFournisseur.getAdresse())
                .matricule(detailFournisseur.getMatricule())
                .build();
    }


    public static DetailFournisseur toEntity(DetailFournisseurDto detailFournisseurDto){
        if(detailFournisseurDto == null) throw new FournisseurException(" is empty");
        return DetailFournisseur.builder()
                .idDetailFournisseur(detailFournisseurDto.getIdDetailFournisseur())
                .email(detailFournisseurDto.getEmail())
                .dateDebutCollaboration(detailFournisseurDto.getDateDebutCollaboration())
                .adresse(detailFournisseurDto.getAdresse())
                .matricule(detailFournisseurDto.getMatricule())
                .build();
    }

}
