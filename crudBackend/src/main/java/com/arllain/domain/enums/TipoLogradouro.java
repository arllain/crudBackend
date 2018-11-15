package com.arllain.domain.enums;

public enum TipoLogradouro {
	
	AVENIDA(1, "Avenida"),
	PATIO(2,"Pátio"),
	PRACA(3,"Praça"),
	QUADRA(4,"Quadra"),
	RECANTO(5,"Recanto"),
	VIA(6,"Via"),
	VIADUTO(7,"Viaduto"),
	VIELA(8,"Viela"),
	VILA(9,"Vila"),
	RUA(10, "Rua"),
	TRAVESSA(11, "Travessa");
	
	private int cod;
	private String descricao;
	/**
	 * @param cod
	 * @param descricao
	 */
	private TipoLogradouro(int cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}
	/**
	 * @return the cod
	 */
	public int getCod() {
		return cod;
	}
	/**
	 * @param cod the cod to set
	 */
	public void setCod(int cod) {
		this.cod = cod;
	}
	/**
	 * @return the descricao
	 */
	public String getDescricao() {
		return descricao;
	}
	/**
	 * @param descricao the descricao to set
	 */
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public static TipoLogradouro toEnum(Integer cod) {
		if(cod == null) {
			return null;
		}
		
		for(TipoLogradouro tipoLogradouro: TipoLogradouro.values()) {
			if(cod.equals(tipoLogradouro.getCod())){
				return tipoLogradouro;
			}
		}
		throw new IllegalArgumentException("código inválido: " + cod);
	}	

}
