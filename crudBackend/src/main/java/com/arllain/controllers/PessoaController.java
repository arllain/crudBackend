package com.arllain.controllers;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.arllain.domain.Pessoa;
import com.arllain.dto.PessoaDTO;
import com.arllain.dto.PessoaNewDTO;
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
	
	
	@RequestMapping(value="/save", method=RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody PessoaNewDTO pessoaNewDTO) {
		Pessoa pessoa = service.fromDTO(pessoaNewDTO);
		pessoa = service.insert(pessoa);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(pessoa.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value="/save", method=RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody PessoaDTO pessoaDTO) {
		Pessoa pessoa = service.fromDTO(pessoaDTO);
		pessoa = service.update(pessoa);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(pessoa.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	public ResponseEntity<Void> update(@Valid @RequestBody PessoaDTO pessoaDTO, @PathVariable Integer id) {
		Pessoa pessoa = service.fromDTO(pessoaDTO);
		pessoa.setId(id);
		pessoa = service.update(pessoa);
		return ResponseEntity.noContent().build();
	}
	

	
}
