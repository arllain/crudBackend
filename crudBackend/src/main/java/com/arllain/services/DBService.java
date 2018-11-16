package com.arllain.services;

import java.text.ParseException;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arllain.domain.Cidade;
import com.arllain.domain.Endereco;
import com.arllain.domain.Estado;
import com.arllain.domain.Pessoa;
import com.arllain.domain.Telefone;
import com.arllain.domain.enums.TipoLogradouro;
import com.arllain.domain.enums.TipoTelefone;
import com.arllain.repositories.CidadeRepository;
import com.arllain.repositories.EnderecoRepository;
import com.arllain.repositories.EstadoRepository;
import com.arllain.repositories.PessoaRepository;
import com.arllain.repositories.TelefoneRepository;

@Service
public class DBService {

	@Autowired
	private PessoaRepository pessoaRepository;

	@Autowired
	private CidadeRepository cidadeRepository;

	@Autowired
	private EstadoRepository estadoRepository;

	@Autowired
	private EnderecoRepository enderecoRepository;

	@Autowired
	private TelefoneRepository telefoneRepository;

	public void instantiateTestDataBase() throws ParseException {
		
		Estado estado1 = new Estado(null, "Pernambuco");
		Estado estado2 = new Estado(null, "Ceará");

		Cidade cid1 = new Cidade(null, "Recife", estado1);
		Cidade cid2 = new Cidade(null, "Caruaru", estado1);
		Cidade cid3 = new Cidade(null, "Fortaleza", estado2);
		Cidade cid4 = new Cidade(null, "Aquiraz", estado2);

		estado1.getCidades().addAll(Arrays.asList(cid1, cid2));
		estado2.getCidades().addAll(Arrays.asList(cid3, cid4));

		estadoRepository.saveAll(Arrays.asList(estado1, estado2));
		cidadeRepository.saveAll(Arrays.asList(cid1, cid2, cid3, cid4));
		
		Pessoa pessoa1 = new Pessoa(null, "Terezinha de Melo", "25156551653");
		Telefone tel1 = new Telefone(null, TipoTelefone.FIXO, "32259885", pessoa1);
		pessoa1.getTelefones().addAll(Arrays.asList(tel1));

		Pessoa pessoa2 = new Pessoa(null, "Daniel Candido", "25156551653");
		Telefone tel2 = new Telefone(null, TipoTelefone.FIXO, "27363323", pessoa2);
		Telefone tel3 = new Telefone(null, TipoTelefone.CELULAR, "93838396", pessoa2);
		pessoa2.getTelefones().addAll(Arrays.asList(tel2, tel3));

		Endereco end1 = new Endereco(null, TipoLogradouro.RUA, "das flores", "300", "Torre", pessoa1, cid1); 
		Endereco end2 = new Endereco(null, TipoLogradouro.AVENIDA, "Domingos Ferreira", "1005", "Boa viagem", pessoa2, cid1);
		Endereco end3 = new Endereco(null, TipoLogradouro.AVENIDA, "dos Programadores", "2020", "Boa viagem", pessoa2, cid1);

		pessoa1.getEnderecos().addAll(Arrays.asList(end1));
		pessoa2.getEnderecos().addAll(Arrays.asList(end2,end3));
		
		pessoaRepository.saveAll(Arrays.asList(pessoa1, pessoa2));
		enderecoRepository.saveAll(Arrays.asList(end1, end2, end3));
		telefoneRepository.saveAll(Arrays.asList(tel1, tel2, tel3));
	}
}
