package com.esprit.examen.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Builder;
import javax.persistence.FetchType;
import javax.persistence.CascadeType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Enumerated;
import javax.persistence.EnumType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Fournisseur implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idFournisseur;
	private String code;
	private String libelle;
	@Enumerated(EnumType.STRING)
	private CategorieFournisseur  categorieFournisseur;
	@OneToMany(mappedBy="fournisseur")
	@JsonIgnore
	private Set<Facture> factures;
    @ManyToMany
    @JsonIgnore
    private Set<SecteurActivite> secteurActivites;
    @OneToOne(cascade= CascadeType.ALL,fetch=FetchType.EAGER)
    private DetailFournisseur detailFournisseur;

	public Fournisseur(long idFournisseur, String code, String libelle, CategorieFournisseur categorieFournisseur) {
		super();
		this.idFournisseur=idFournisseur;
		this.code = code;
		this.libelle = libelle;
		this.categorieFournisseur = categorieFournisseur;
	}
	public Fournisseur(String code, String libelle, CategorieFournisseur categorieFournisseur) {
		super();
		this.code = code;
		this.libelle = libelle;
		this.categorieFournisseur = categorieFournisseur;
	}
}
