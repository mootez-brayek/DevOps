package com.esprit.examen.controllers;

import java.util.List;

import com.esprit.examen.dto.OperateurDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.esprit.examen.services.IOperateurService;

import io.swagger.annotations.Api;

@RestController
@Api(tags = "Gestion des opérateurs")
@RequestMapping("/operateur")
@CrossOrigin("*")
public class OperateurController {

	@Autowired
	IOperateurService operateurService;

	// http://localhost:8089/SpringMVC/operateur/retrieve-all-operateurs
	@GetMapping("/retrieve-all-operateurs")
	@ResponseBody
	public List<OperateurDto> getOperateurs() {
		return operateurService.retrieveAllOperateurs();

	}

	// http://localhost:8089/SpringMVC/operateur/retrieve-operateur/8
	@GetMapping("/retrieve-operateur/{operateur-id}")
	@ResponseBody
	public OperateurDto retrieveOperateur(@PathVariable("operateur-id") Long operateurId) {
		return operateurService.retrieveOperateur(operateurId);
	}

	// http://localhost:8089/SpringMVC/operateur/add-operateur
	@PostMapping("/add-operateur")
	@ResponseBody
	public OperateurDto addOperateur(@RequestBody OperateurDto op) {
		return operateurService.addOperateur(op);

	}

	// http://localhost:8089/SpringMVC/operateur/remove-operateur/{operateur-id}
	@DeleteMapping("/remove-operateur/{operateur-id}")
	@ResponseBody
	public void removeOperateur(@PathVariable("operateur-id") Long operateurId) {
		operateurService.deleteOperateur(operateurId);
	}

	// http://localhost:8089/SpringMVC/operateur/modify-operateur
	@PutMapping("/modify-operateur")
	@ResponseBody
	public OperateurDto modifyOperateur(@RequestBody OperateurDto operateur) {
		return operateurService.updateOperateur(operateur);
	}

	
}
