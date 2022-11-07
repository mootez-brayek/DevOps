package com.esprit.examen.dto;


import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.esprit.examen.entities.CategorieProduit;
import com.esprit.examen.entities.DetailFacture;
import com.esprit.examen.entities.Stock;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProduitDto implements Serializable{
    private static final long serialVersionUID = 1L;

    private Long idProduit;
    private String codeProduit;
    private String libelleProduit;
    private float prix;
    @Temporal(TemporalType.DATE)
    private Date dateCreation;
    @Temporal(TemporalType.DATE)
    private Date dateDerniereModification;
    @JsonIgnore
    private Stock stock;
    @JsonIgnore
    private Set<DetailFacture> detailFacture;
    @JsonIgnore
    private CategorieProduit categorieProduit;
}
