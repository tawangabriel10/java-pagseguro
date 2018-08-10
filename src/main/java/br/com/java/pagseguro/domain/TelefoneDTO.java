package br.com.java.pagseguro.domain;

import java.io.Serializable;

public class TelefoneDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7738258343700536826L;

	private Long id;
	
	private String codigoArea;
	
	private String numero;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCodigoArea() {
		return codigoArea;
	}

	public void setCodigoArea(String codigoArea) {
		this.codigoArea = codigoArea;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}
	
	
}
