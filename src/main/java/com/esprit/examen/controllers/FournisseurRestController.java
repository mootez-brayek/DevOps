package com.esprit.examen.controllers;

import com.esprit.examen.entities.Fournisseur;
import com.esprit.examen.services.IFournisseurService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@Api(tags = "Gestion des fournisseurss")
@RequestMapping("/fournisseur")
public class FournisseurRestController {

	@Autowired
	IFournisseurService fournisseurService;

	// http://localhost:8089/SpringMVC/fournisseur/retrieve-all-fournisseurs
	@GetMapping("/retrieve-all-fournisseurs")
	@ResponseBody
	public List<Fournisseur> getFournisseurs() {
		return fournisseurService.retrieveAllFournisseurs();
	}

	// http://localhost:8089/SpringMVC/fournisseur/retrieve-fournisseur/8
	@GetMapping("/retrieve-fournisseur/{fournisseur-id}")
	@ResponseBody
	public Fournisseur retrieveFournisseur(@PathVariable("fournisseur-id") Long fournisseurId) {
		return fournisseurService.retrieveFournisseur(fournisseurId);
	}

	// http://localhost:8089/SpringMVC/fournisseur/add-fournisseur
	@PostMapping("/add-fournisseur")
	@ResponseBody
	public Fournisseur addFournisseur(@RequestBody Fournisseur f) {
		return fournisseurService.addFournisseur(f);
	}

	@DeleteMapping("/remove-fournisseur/{fournisseur-id}")
	@ResponseBody
	public void removeFournisseur(@PathVariable("fournisseur-id") Long fournisseurId) {
		fournisseurService.deleteFournisseur(fournisseurId);
	}

	// http://localhost:8089/SpringMVC/fournisseur/modify-fournisseur
	@PutMapping("/modify-fournisseur")
	@ResponseBody
	public Fournisseur modifyFournisseur(@RequestBody Fournisseur fournisseur) {
		return fournisseurService.updateFournisseur(fournisseur);
	}

	// http://localhost:8089/SpringMVC/fournisseur/assignSecteurActiviteToFournisseur/1/5
		@PutMapping(value = "/assignSecteurActiviteToFournisseur/{idSecteurActivite}/{idFournisseur}")
		public void assignProduitToStock(@PathVariable("idSecteurActivite") Long idSecteurActivite, @PathVariable("idFournisseur") Long idFournisseur) {
			fournisseurService.assignSecteurActiviteToFournisseur(idSecteurActivite, idFournisseur);
		}

}
