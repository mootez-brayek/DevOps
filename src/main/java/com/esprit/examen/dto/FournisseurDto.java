package com.esprit.examen.dto;


import com.esprit.examen.exceptions.InvalidEntityException;
import com.esprit.examen.entities.CategorieFournisseur;
import com.esprit.examen.entities.Fournisseur;
import lombok.Builder;
import lombok.Data;
import javax.persistence.EntityNotFoundException;
@Data
@Builder
public class FournisseurDto {

    private Long idFournisseur;
    private String code;
    private String libelle;
    private CategorieFournisseur categorieFournisseur;

    public static Fournisseur toEntity(FournisseurDto dto){
        if(dto==null){
            throw new InvalidEntityException("données entrées invalide");
        }
        return Fournisseur
                .builder()
                .idFournisseur(dto.idFournisseur)
                .code(dto.code)
                .libelle(dto.libelle)
                .categorieFournisseur(dto.categorieFournisseur)
                .build();
    }
}
