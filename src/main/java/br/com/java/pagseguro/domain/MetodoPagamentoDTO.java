package br.com.java.pagseguro.domain;

import br.com.uol.pagseguro.api.common.domain.enums.PaymentMethodGroup;
import br.com.uol.pagseguro.api.common.domain.enums.PaymentMethodName;

import java.io.Serializable;


public class MetodoPagamentoDTO implements Serializable {
	
	private PaymentMethodName nome;
	
	private PaymentMethodGroup grupo;
	
	private Boolean isConfig;
	
	private ConfigMetodoPagamentoDTO configMetodo;

	public PaymentMethodName getNome() {
		return nome;
	}

	public void setNome(PaymentMethodName nome) {
		this.nome = nome;
	}

	public PaymentMethodGroup getGrupo() {
		return grupo;
	}

	public void setGrupo(PaymentMethodGroup grupo) {
		this.grupo = grupo;
	}

	public Boolean getConfig() {
		return isConfig;
	}

	public void setConfig(Boolean config) {
		isConfig = config;
	}

	public ConfigMetodoPagamentoDTO getConfigMetodo() {
		return configMetodo;
	}

	public void setConfigMetodo(ConfigMetodoPagamentoDTO configMetodo) {
		this.configMetodo = configMetodo;
	}
}
