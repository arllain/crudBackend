package com.arllain.controllers;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.arllain.domain.Pessoa;
import com.arllain.dto.PessoaDTO;
import com.arllain.services.PessoaService;

@RestController
@RequestMapping(value="/rest/pessoas")
public class PessoaController {
	
	@Autowired
	private PessoaService service;

	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<PessoaDTO>> findAll() {
		List<Pessoa> lista = service.findAll();
		List<PessoaDTO> listaDTO = lista.stream().map(pessoa -> new PessoaDTO(pessoa)).collect(Collectors.toList());	
		return ResponseEntity.ok(listaDTO);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<Pessoa> find(@PathVariable Integer id) {
		Pessoa pessoa = service.find(id);
		return ResponseEntity.ok(pessoa);
	}
}