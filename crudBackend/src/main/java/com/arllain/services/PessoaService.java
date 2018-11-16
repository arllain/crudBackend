package com.arllain.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arllain.domain.Cidade;
import com.arllain.domain.Endereco;
import com.arllain.domain.Pessoa;
import com.arllain.domain.Telefone;
import com.arllain.domain.enums.TipoLogradouro;
import com.arllain.domain.enums.TipoTelefone;
import com.arllain.dto.PessoaNewDTO;
import com.arllain.repositories.CidadeRepository;
import com.arllain.repositories.EnderecoRepository;
import com.arllain.repositories.PessoaRepository;
import com.arllain.services.exception.DataIntegrityException;
import com.arllain.services.exception.ObjectNotFoundException;

@Service
public class PessoaService {

	@Autowired
	private PessoaRepository pessoaRepo;

	@Autowired
	private EnderecoRepository enderoRepo;

	@Autowired
	private CidadeRepository cidadeRepo;

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

	public Pessoa fromDTO(@Valid PessoaNewDTO pessoaNewDTO) {

		Pessoa pessoa = new Pessoa(null, pessoaNewDTO.getNome(), pessoaNewDTO.getCpf());

		Optional<Cidade> cidade = cidadeRepo.findById(pessoaNewDTO.getCidadeId());

		Endereco endereco = new Endereco(null, TipoLogradouro.toEnum(pessoaNewDTO.getTipoLogradouro()),
				pessoaNewDTO.getLogradouro(), pessoaNewDTO.getNumero(), pessoaNewDTO.getBairro(), pessoa, cidade.get());

		pessoa.getEnderecos().add(endereco);
		
		Telefone tel1 = new Telefone(null, TipoTelefone.toEnum(null), pessoaNewDTO.getTelefone1(), pessoa);
		pessoa.getTelefones().add(tel1);
		
		if (pessoaNewDTO.getTelefone2() != null) {
			Telefone tel2 = new Telefone(null, TipoTelefone.toEnum(null), pessoaNewDTO.getTelefone1(), pessoa);
			pessoa.getTelefones().add(tel2);
		}

		if (pessoaNewDTO.getTelefone3() != null) {
			Telefone tel3 = new Telefone(null, TipoTelefone.toEnum(null), pessoaNewDTO.getTelefone1(), pessoa);
			pessoa.getTelefones().add(tel3);
		}
		
		return pessoa;
	}

}
