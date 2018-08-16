package br.com.java.pagseguro.domain;

import java.io.Serializable;

public class CartaoCreditoDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6666112398025610290L;

	private String token;

	private TitularDTO titular;
	
	private EnderecoDTO enderecoCobranca;
	
	private PrestacaoDTO prestacao;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public TitularDTO getTitular() {
		return titular;
	}

	public void setTitular(TitularDTO titular) {
		this.titular = titular;
	}

	public EnderecoDTO getEnderecoCobranca() {
		return enderecoCobranca;
	}

	public void setEnderecoCobranca(EnderecoDTO enderecoCobranca) {
		this.enderecoCobranca = enderecoCobranca;
	}

	public PrestacaoDTO getPrestacao() {
		return prestacao;
	}

	public void setPrestacao(PrestacaoDTO prestacao) {
		this.prestacao = prestacao;
	}
	
}
