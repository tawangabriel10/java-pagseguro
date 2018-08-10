package br.com.java.pagseguro.domain;

import java.io.Serializable;

import br.com.uol.pagseguro.api.common.domain.enums.State;

public class EnderecoDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3653697878417768782L;

	private String codigoPostal;
	
	private String pais;
	
	private State estado;
	
	private String cidade;
	
	private String complemento;
	
	private String distrito;
	
	private String numero;
	
	private String rua;

	public String getCodigoPostal() {
		return codigoPostal;
	}

	public void setCodigoPostal(String codigoPostal) {
		this.codigoPostal = codigoPostal;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public State getEstado() {
		return estado;
	}

	public void setEstado(State estado) {
		this.estado = estado;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getDistrito() {
		return distrito;
	}

	public void setDistrito(String distrito) {
		this.distrito = distrito;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}
	
	
}
