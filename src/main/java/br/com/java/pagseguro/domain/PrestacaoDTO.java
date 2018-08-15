package br.com.java.pagseguro.domain;

import java.io.Serializable;
import java.math.BigDecimal;

public class PrestacaoDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3179663878803239655L;
	
	private Integer quantidade;
	
	private BigDecimal valor;

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	
}
