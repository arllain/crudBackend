package com.arllain.domain;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import com.arllain.domain.enums.TipoLogradouro;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Endereco implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private Integer tipoLogradouro;
	private String logradouro;
	private String numero;
	private String bairro;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="pessoa_id")
	private Pessoa pessoa;

	@ManyToOne
	@JoinColumn(name="cidade_id")
	private Cidade cidade;
	
	@SuppressWarnings("unused")
	private Endereco() {
	}

	/**
	 * @param id
	 * @param TipoLogradouro
	 * @param logradouro
	 * @param numero
	 * @param bairro
	 * @param pessoa
	 * @param cidade
	 */
	public Endereco(Integer id, TipoLogradouro tipoLogradouro, String logradouro, String numero, String bairro, 
			Pessoa pessoa, Cidade cidade) {
		super();
		this.id = id;
		this.tipoLogradouro = (tipoLogradouro == null) ? TipoLogradouro.RUA.getCod() : tipoLogradouro.getCod();
		this.logradouro = logradouro;
		this.numero = numero;
		this.bairro = bairro;
		this.pessoa = pessoa;
		this.cidade = cidade;
	}

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	
	/**
	 * @return the tipoLogradouro
	 */
	public TipoLogradouro getTipo() {
		return TipoLogradouro.toEnum(tipoLogradouro);
	}

	/**
	 * @param tipo the tipoLogradouro to set
	 */
	public void setTipo(TipoLogradouro tipoLogradouro) {
		this.tipoLogradouro = tipoLogradouro.getCod();
	}

	/**
	 * @return the logradouro
	 */
	public String getLogradouro() {
		return logradouro;
	}

	/**
	 * @param logradouro the logradouro to set
	 */
	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	/**
	 * @return the numero
	 */
	public String getNumero() {
		return numero;
	}

	/**
	 * @param numero the numero to set
	 */
	public void setNumero(String numero) {
		this.numero = numero;
	}

	/**
	 * @return the bairro
	 */
	public String getBairro() {
		return bairro;
	}

	/**
	 * @param bairro the bairro to set
	 */
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	/**
	 * @return the cliente
	 */
	
	@JsonIgnore
	public Pessoa getCliente() {
		return pessoa;
	}

	/**
	 * @param pessoa the cliente to set
	 */
	@JsonIgnore
	public void setCliente(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	/**
	 * @return the cidade
	 */
	public Cidade getCidade() {
		return cidade;
	}

	/**
	 * @param cidade the cidade to set
	 */
	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Endereco other = (Endereco) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
