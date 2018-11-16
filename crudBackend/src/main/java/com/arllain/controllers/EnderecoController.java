package com.arllain.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.arllain.domain.Endereco;
import com.arllain.dto.PessoaDTO;
import com.arllain.services.EnderecoService;

@RestController
@RequestMapping(value="/rest/endereco")
public class EnderecoController {
	
	@Autowired
	private EnderecoService service;

	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<PessoaDTO>> findAll() {
//		List<Endereco> lista = service.findAll();
//		List<PessoaDTO> listaDTO = lista.stream().map(pessoa -> new PessoaDTO(pessoa)).collect(Collectors.toList());	
		return null;//ResponseEntity.ok(listaDTO);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<Endereco> find(@PathVariable Integer id) {
		Endereco endereco = service.find(id);
		return ResponseEntity.ok(endereco);
	}
}
