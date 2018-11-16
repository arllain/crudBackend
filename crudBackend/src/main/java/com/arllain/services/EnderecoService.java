package com.arllain.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arllain.domain.Endereco;
import com.arllain.repositories.EnderecoRepository;
import com.arllain.services.exception.ObjectNotFoundException;

@Service
public class EnderecoService {

	@Autowired
	private EnderecoRepository enderocoRepo;

	
//	@Transactional
//	public Pessoa insert(Pessoa pessoa) {
//		pessoa.setId(null);
//		pessoa = pessoaRepo.save(pessoa);
//		//enderoRepo.saveAll(pessoa.getEnderecos());
//		return pessoa;
//	}
//
//	public Pessoa update(Pessoa pessoa) {
//		Pessoa p = find(pessoa.getId());
//		p.setNome(pessoa.getNome());
//		return pessoaRepo.save(p);
//	}
//
//	public void delete(Integer id) {
//		find(id);
//		try {
//			pessoaRepo.deleteById(id);
//		} catch (DataIntegrityViolationException e) {
//			throw new DataIntegrityException("Não é possível excluir porque há entidades relacionadas.");
//		}
//	}
	
	public Endereco find(Integer id) {
		Optional<Endereco> pessoa = enderocoRepo.findById(id);
		return pessoa.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Endereco.class.getName()));
	}

	public List<Endereco> findAll() {
		return enderocoRepo.findAll();
	}
}
