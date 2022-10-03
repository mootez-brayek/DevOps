package com.esprit.examen.services;

import java.util.Date;
import java.util.List;

import com.esprit.examen.dto.ReglementDto;
import com.esprit.examen.exceptions.ReglementNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import com.esprit.examen.entities.Reglement;
import com.esprit.examen.repositories.ReglementRepository;

@Service
@AllArgsConstructor
public class ReglementServiceImpl implements IReglementService {
	private final ReglementRepository reglementRepository;
	@Override
	public List<ReglementDto> retrieveAllReglements() {
		return ReglementDto.toListOfDto((List<Reglement>)reglementRepository.findAll());
	}

	@Override
	public ReglementDto addReglement(ReglementDto reglementDto) {
		return ReglementDto.toDto(reglementRepository.save(ReglementDto.toEntity(reglementDto)));
	}

	@Override
	public ReglementDto retrieveReglement(Long id) {
		return reglementRepository
				.findById(id)
				.map(ReglementDto::toDto)
				.orElseThrow(()->new ReglementNotFoundException("reglement avec l'id : "+id +" est introuvable"));
	}

	@Override
	public List<ReglementDto> retrieveReglementByFacture(Long idFacture) {
		return ReglementDto.toListOfDto(reglementRepository.retrieveReglementByFacture(idFacture));
	}

	@Override
	public float getChiffreAffaireEntreDeuxDate(Date startDate, Date endDate) {
		return reglementRepository.getChiffreAffaireEntreDeuxDate( startDate, endDate);
	}

}
