package com.esprit.examen.services;

import java.util.Date;
import java.util.List;

import com.esprit.examen.dto.ReglementDto;
import com.esprit.examen.exceptions.ReglementNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import com.esprit.examen.entities.Reglement;
import com.esprit.examen.repositories.ReglementRepository;

@Service
@AllArgsConstructor
@Slf4j
public class ReglementServiceImpl implements IReglementService {
	private final ReglementRepository reglementRepository;
	@Override
	public List<ReglementDto> retrieveAllReglements() {
		return ReglementDto.toListOfDto((List<Reglement>)reglementRepository.findAll());
	}

	@Override
	public ReglementDto addReglement(ReglementDto reglementDto) {
		long start = System.currentTimeMillis();
		log.info("In method addReglement of ReglementServic");
		ReglementDto result = ReglementDto.toDto(reglementRepository.save(ReglementDto.toEntity(reglementDto)));
		log.info("out of method addReglement ReglementServic");
		long elapsedTime = System.currentTimeMillis() - start;
		log.info("Method execution time: " + elapsedTime + " milliseconds.");
		return result;
	}

	@Override
	public ReglementDto retrieveReglement(Long id) {
		long start = System.currentTimeMillis();
		log.info("In method retrieveReglement of ReglementServic");
		ReglementDto result =  reglementRepository
				.findById(id)
				.map(ReglementDto::toDto)
				.orElseThrow(()->new ReglementNotFoundException("reglement avec l'id : "+id +" est introuvable"));
		log.info("out of method retrieveReglement ReglementServic");
		long elapsedTime = System.currentTimeMillis() - start;
		log.info("Method execution time: " + elapsedTime + " milliseconds.");
		return result;
	}

	@Override
	public void deleteReglement(ReglementDto r) {
		long start = System.currentTimeMillis();
		log.info("In method deleteReglement of ReglementServic");
		reglementRepository.delete(ReglementDto.toEntity(r));
		log.info("out of method deleteReglement ReglementServic");
		long elapsedTime = System.currentTimeMillis() - start;
		log.info("Method execution time: " + elapsedTime + " milliseconds.");
	}

	@Override
	public List<ReglementDto> retrieveReglementByFacture(Long idFacture) {
		long start = System.currentTimeMillis();
		log.info("In method retrieveReglementByFacture of ReglementServic");
		List<ReglementDto> result =  ReglementDto.toListOfDto(reglementRepository.retrieveReglementByFacture(idFacture));
		log.info("out of method retrieveReglementByFacture ReglementServic");
		long elapsedTime = System.currentTimeMillis() - start;
		log.info("Method execution time: " + elapsedTime + " milliseconds.");
		return result;
	}

	@Override
	public float getChiffreAffaireEntreDeuxDate(Date startDate, Date endDate) {
		long start = System.currentTimeMillis();
		log.info("In method retrieveReglementByFacture of ReglementServic");
		log.info("out of method retrieveReglementByFacture ReglementServic");
		float result = reglementRepository.getChiffreAffaireEntreDeuxDate( startDate, endDate);
		long elapsedTime = System.currentTimeMillis() - start;
		log.info("Method execution time: " + elapsedTime + " milliseconds.");
		return result;
	}

}
