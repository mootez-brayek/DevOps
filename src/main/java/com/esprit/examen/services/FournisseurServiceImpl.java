package com.esprit.examen.services;

import com.esprit.examen.entities.DetailFournisseur;
import com.esprit.examen.entities.Fournisseur;
import com.esprit.examen.repositories.DetailFournisseurRepository;
import com.esprit.examen.repositories.FournisseurRepository;
import com.esprit.examen.repositories.ProduitRepository;
import com.esprit.examen.repositories.SecteurActiviteRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class FournisseurServiceImpl implements IFournisseurService {

	@Autowired
	FournisseurRepository fournisseurRepository;
	@Autowired
	DetailFournisseurRepository detailFournisseurRepository;
	@Autowired
	ProduitRepository produitRepository;
	@Autowired
	SecteurActiviteRepository secteurActiviteRepository;

	@Override
	public List<Fournisseur> retrieveAllFournisseurs() {
		return fournisseurRepository.findAll();
	}

	public Fournisseur addFournisseur(Fournisseur f /*Master*/) {
		var detailFournisseurf= new DetailFournisseur();//Slave
		detailFournisseurf.setDateDebutCollaboration(new Date()); //util
		f.setDetailFournisseur(detailFournisseurf);
		fournisseurRepository.save(f);
		return f;
	}

	private DetailFournisseur  saveDetailFournisseur(Fournisseur f){
		var detailFournisseurf = f.getDetailFournisseur();
		detailFournisseurRepository.save(detailFournisseurf);
		return detailFournisseurf;
	}

	public Fournisseur updateFournisseur(Fournisseur f) {
		var detailFournisseurf = saveDetailFournisseur(f);
		f.setDetailFournisseur(detailFournisseurf);
		fournisseurRepository.save(f);
		return f;
	}

	@Override
	public void deleteFournisseur(Long fournisseurId) {
		fournisseurRepository.deleteById(fournisseurId);

	}

	@Override
	public Fournisseur retrieveFournisseur(Long fournisseurId) {
		return fournisseurRepository.findById(fournisseurId).orElse(null);
	}

	@Override
	public void assignSecteurActiviteToFournisseur(Long idSecteurActivite,Long idFournisseur) {
		var fournisseur = fournisseurRepository.findById(idFournisseur).orElse(null);
		if (fournisseur != null){
		var secteurActivite = secteurActiviteRepository.findById(idSecteurActivite).orElse(null);
		fournisseur.getSecteurActivites().add(secteurActivite);
		fournisseurRepository.save(fournisseur);}

	}

	

}