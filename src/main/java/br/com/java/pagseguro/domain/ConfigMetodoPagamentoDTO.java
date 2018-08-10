package br.com.java.pagseguro.domain;

import java.io.Serializable;
import java.math.BigDecimal;

import br.com.uol.pagseguro.api.common.domain.enums.ConfigKey;

public class ConfigMetodoPagamentoDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3761767347198239951L;

	private ConfigKey chave;
	
	private BigDecimal valor;

	public ConfigKey getChave() {
		return chave;
	}

	public void setChave(ConfigKey chave) {
		this.chave = chave;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	
	
}
