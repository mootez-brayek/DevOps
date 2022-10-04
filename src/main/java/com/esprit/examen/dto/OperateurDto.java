package com.esprit.examen.dto;

import com.esprit.examen.entities.Operateur;
import com.esprit.examen.exceptions.InvalidEntityException;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
public class OperateurDto {
    private Long idOperateur;
    private String nom;
    private String prenom;

    private String password;

    public static OperateurDto toDto(Operateur operateur) {
        if (operateur == null) {throw new InvalidEntityException("objet invalide");}
         return OperateurDto.builder()
                 .idOperateur(operateur.getIdOperateur())
                 .nom(operateur.getNom())
                 .prenom(operateur.getPrenom())
                 .password(operateur.getPassword())
                 .build();
    }
    public static Operateur toOperateur(OperateurDto operateurdto) {
        if (operateurdto == null) throw new InvalidEntityException("objet invalide");
        return Operateur.builder()
                .idOperateur(operateurdto.getIdOperateur())
                .nom(operateurdto.getNom())
                .prenom(operateurdto.getPrenom())
                .password(operateurdto.getPassword())
                .build();
    }
    public static List<OperateurDto> toDtoList(List<Operateur> operateurs) {
        List<OperateurDto> list = new ArrayList<>();
          operateurs.forEach(operateur -> list.add(OperateurDto.toDto(operateur)));
            return list ; 


    }
}
