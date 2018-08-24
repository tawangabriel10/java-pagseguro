package br.com.java.pagseguro.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import br.com.uol.pagseguro.api.common.domain.enums.Currency;

public class PreAprovadoDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4682399098505830831L;

	private String redirecionaURL;
	private String notificacaoRL;
	private Currency moeda;
	private BigDecimal precoExtra;
	private String referencia;
	private RemessaDTO remessa;
	private RemetenteDTO remetente;
	private RequisicaoPreAprovadoDTO requisicao;
	private Map<String, String> parametros = new HashMap<String, String>();
	
	public String getRedirecionaURL() {
		return redirecionaURL;
	}
	public void setRedirecionaURL(String redirecionaURL) {
		this.redirecionaURL = redirecionaURL;
	}
	public String getNotificacaoRL() {
		return notificacaoRL;
	}
	public void setNotificacaoRL(String notificacaoRL) {
		this.notificacaoRL = notificacaoRL;
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
	public RemessaDTO getRemessa() {
		return remessa;
	}
	public void setRemessa(RemessaDTO remessa) {
		this.remessa = remessa;
	}
	public RemetenteDTO getRemetente() {
		return remetente;
	}
	public void setRemetente(RemetenteDTO remetente) {
		this.remetente = remetente;
	}
	public RequisicaoPreAprovadoDTO getRequisicao() {
		return requisicao;
	}
	public void setRequisicao(RequisicaoPreAprovadoDTO requisicao) {
		this.requisicao = requisicao;
	}
	public Map<String, String> getParametros() {
		return parametros;
	}
	public void setParametros(Map<String, String> parametros) {
		this.parametros = parametros;
	}
	
}
