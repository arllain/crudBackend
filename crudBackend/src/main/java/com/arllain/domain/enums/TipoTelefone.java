package com.arllain.domain.enums;

public enum TipoTelefone {
	
	FIXO(1, "Fixo"),
	CELULAR(2,"Celular");
	
	private int cod;
	private String descricao;
	/**
	 * @param cod
	 * @param descricao
	 */
	private TipoTelefone(int cod, String descricao) {
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
	
	public static TipoTelefone toEnum(Integer cod) {
		if(cod == null) {
			return null;
		}
		
		for(TipoTelefone tipoTelefone: TipoTelefone.values()) {
			if(cod.equals(tipoTelefone.getCod())){
				return tipoTelefone;
			}
		}
		throw new IllegalArgumentException("código inválido: " + cod);
	}	

}
