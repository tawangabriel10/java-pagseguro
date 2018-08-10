package br.com.java.pagseguro.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import br.com.uol.pagseguro.api.common.domain.enums.Currency;

public class PagamentoDTO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1396721669039004741L;

	private Long id;
	
	private Currency moeda;
	
	private BigDecimal precoExtra;
	
	private String referencia;
	
	private RemetenteDTO remetente;
	
	private RemessaDTO remessa;
	
	private List<ProdutoDTO> produtos;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Currency getMoeda() {
		return moeda;
	}

	public void setMoeda(Currency moeda) {
		this.moeda = moeda;
	}

	public BigDecimal getPrecoExtra() {
		return precoExtra;
	}

	public void setPrecoExtra(BigDecimal precoExtra) {
		this.precoExtra = precoExtra;
	}

	public String getReferencia() {
		return referencia;
	}

	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}

	public RemetenteDTO getRemetente() {
		return remetente;
	}

	public void setRemetente(RemetenteDTO remetente) {
		this.remetente = remetente;
	}

	public RemessaDTO getRemessa() {
		return remessa;
	}

	public void setRemessa(RemessaDTO remessa) {
		this.remessa = remessa;
	}

	public List<ProdutoDTO> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<ProdutoDTO> produtos) {
		this.produtos = produtos;
	}
	

}
