package com.esprit.examen.services;

import java.util.List;

import com.esprit.examen.dto.OperateurDto;
import com.esprit.examen.repositories.OperateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.esprit.examen.entities.Operateur;


@Service
public class OperateurServiceImpl implements IOperateurService {

	@Autowired
	OperateurRepository operateurRepository;
	@Override
	public List<OperateurDto> retrieveAllOperateurs() {
		return OperateurDto.toDtoList ((List<Operateur>) operateurRepository.findAll());
	}

	@Override
	public OperateurDto addOperateur(OperateurDto o) {
		operateurRepository.save(OperateurDto.toOperateur(o));
		return  o;
	}

	@Override
	public void deleteOperateur(Long id) {
		operateurRepository.deleteById(id);
		
	}

	@Override
	public OperateurDto updateOperateur(OperateurDto o) {
		operateurRepository.save(OperateurDto.toOperateur(o));
		return o;
	}

	@Override
	public OperateurDto retrieveOperateur(Long id) {
		Operateur operateur = operateurRepository.findById(id).orElse(null);
		return OperateurDto.toDto(operateur);
	}

}
