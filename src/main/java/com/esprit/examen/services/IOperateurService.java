package com.esprit.examen.services;

import java.util.List;

import com.esprit.examen.dto.OperateurDto;



public interface IOperateurService {

	List<OperateurDto> retrieveAllOperateurs();

	OperateurDto addOperateur(OperateurDto o);

	void deleteOperateur(Long id);

	OperateurDto updateOperateur(OperateurDto o);

	OperateurDto retrieveOperateur(Long id);

}
