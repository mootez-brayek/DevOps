package com.esprit.examen.services;

import java.util.Date;
import java.util.List;
import com.esprit.examen.dto.ReglementDto;
import com.esprit.examen.entities.Reglement;

public interface IReglementService {

	List<Reglement> retrieveAllReglements();
	ReglementDto addReglement(ReglementDto r);
	ReglementDto retrieveReglement(Long id);
	ReglementDto updateReglement(ReglementDto r );
	void deleteReglement(ReglementDto r);
	List<ReglementDto> retrieveReglementByFacture(Long idFacture);
	float getChiffreAffaireEntreDeuxDate(Date startDate, Date endDate); 

}
