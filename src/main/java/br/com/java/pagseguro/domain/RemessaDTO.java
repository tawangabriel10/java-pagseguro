package br.com.java.pagseguro.domain;

import java.io.Serializable;
import java.math.BigDecimal;

import br.com.uol.pagseguro.api.common.domain.ShippingType;

public class RemessaDTO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7573962358475138663L;

	private ShippingType.Type tipo;
	
	private BigDecimal custo;
	
	private EnderecoDTO endereco;

	public ShippingType.Type getTipo() {
		return tipo;
	}

	public void setTipo(ShippingType.Type tipo) {
		this.tipo = tipo;
	}

	public BigDecimal getCusto() {
		return custo;
	}

	public void setCusto(BigDecimal custo) {
		this.custo = custo;
	}

	public EnderecoDTO getEndereco() {
		return endereco;
	}

	public void setEndereco(EnderecoDTO endereco) {
		this.endereco = endereco;
	}

}
