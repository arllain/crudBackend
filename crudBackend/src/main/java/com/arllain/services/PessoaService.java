package com.arllain.services;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.arllain.domain.Pessoa;
import com.arllain.repositories.CidadeRepository;
import com.arllain.repositories.EnderecoRepository;
import com.arllain.services.exception.DataIntegrityException;
import com.arllain.services.exception.ObjectNotFoundException;
import com.arllain.repositories.PessoaRepository;

@Service
public class PessoaService {

	@Autowired
	private PessoaRepository pessoaRepo;

	@Autowired
	private EnderecoRepository enderoRepo;

	
	@Transactional
	public Pessoa insert(Pessoa pessoa) {
		pessoa.setId(null);
		pessoa = pessoaRepo.save(pessoa);
		enderoRepo.saveAll(pessoa.getEnderecos());
		return pessoa;
	}

	public Pessoa update(Pessoa pessoa) {
		Pessoa p = find(pessoa.getId());
		p.setNome(pessoa.getNome());
		return pessoaRepo.save(p);
	}

	public void delete(Integer id) {
		find(id);
		try {
			pessoaRepo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir porque há entidades relacionadas.");
		}
	}
	
	public Pessoa find(Integer id) {
		Optional<Pessoa> pessoa = pessoaRepo.findById(id);
		return pessoa.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Pessoa.class.getName()));
	}

	public List<Pessoa> findAll() {
		return pessoaRepo.findAll();
	}
}
