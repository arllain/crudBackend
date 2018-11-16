package com.arllain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.arllain.domain.Telefone;

@Repository
public interface TelefoneRepository extends JpaRepository<Telefone, Integer>{
	
}
