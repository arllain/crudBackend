package com.arllain.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arllain.domain.Cidade;
import com.arllain.domain.Estado;
import com.arllain.domain.Pessoa;
import com.arllain.repositories.CidadeRepository;
import com.arllain.repositories.EnderecoRepository;
import com.arllain.repositories.EstadoRepository;
import com.arllain.repositories.PessoaRepository;

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
	}
}
