package br.com.java.pagseguro.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.uol.pagseguro.api.common.domain.DateRange;
import br.com.uol.pagseguro.api.common.domain.Expiration;
import br.com.uol.pagseguro.api.common.domain.Parameter;
import br.com.uol.pagseguro.api.common.domain.PreApprovalRequest;
import br.com.uol.pagseguro.api.common.domain.Sender;
import br.com.uol.pagseguro.api.common.domain.Shipping;
import br.com.uol.pagseguro.api.common.domain.builder.DateRangeBuilder;
import br.com.uol.pagseguro.api.common.domain.enums.Currency;

public class RequisicaoPreAprovadoDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 933728537599505165L;

	private String carrega;
	  private String nome;
	  private String detalhes;
	  private BigDecimal percentualExtraPagamento;
	  private BigDecimal valorMaximoPercentualPagamento;
	  private BigDecimal valorMaximoTotal;
	  private BigDecimal valorMaximoPercentualPeriodo;
	  private Integer pagamentoMaximoPercentualPeriodo;
	  private String periodo;
	  private Date dataInicial;
	  private Date dataFinal;
	  private BigDecimal taxaAssociacao;
	  private Integer tentativaPeriodoDuracao;
	  private ExpiracaoDTO expiracao;
	  private String diaDoAno;
	  private Integer diaDoMes;
	  private String diaDaSemana;
	  private String cancelarURL;
	public String getCarrega() {
		return carrega;
	}
	public void setCarrega(String carrega) {
		this.carrega = carrega;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getDetalhes() {
		return detalhes;
	}
	public void setDetalhes(String detalhes) {
		this.detalhes = detalhes;
	}
	public BigDecimal getPercentualExtraPagamento() {
		return percentualExtraPagamento;
	}
	public void setPercentualExtraPagamento(BigDecimal percentualExtraPagamento) {
		this.percentualExtraPagamento = percentualExtraPagamento;
	}
	public BigDecimal getValorMaximoPercentualPagamento() {
		return valorMaximoPercentualPagamento;
	}
	public void setValorMaximoPercentualPagamento(BigDecimal valorMaximoPercentualPagamento) {
		this.valorMaximoPercentualPagamento = valorMaximoPercentualPagamento;
	}
	public BigDecimal getValorMaximoTotal() {
		return valorMaximoTotal;
	}
	public void setValorMaximoTotal(BigDecimal valorMaximoTotal) {
		this.valorMaximoTotal = valorMaximoTotal;
	}
	public BigDecimal getValorMaximoPercentualPeriodo() {
		return valorMaximoPercentualPeriodo;
	}
	public void setValorMaximoPercentualPeriodo(BigDecimal valorMaximoPercentualPeriodo) {
		this.valorMaximoPercentualPeriodo = valorMaximoPercentualPeriodo;
	}
	public Integer getPagamentoMaximoPercentualPeriodo() {
		return pagamentoMaximoPercentualPeriodo;
	}
	public void setPagamentoMaximoPercentualPeriodo(Integer pagamentoMaximoPercentualPeriodo) {
		this.pagamentoMaximoPercentualPeriodo = pagamentoMaximoPercentualPeriodo;
	}
	public String getPeriodo() {
		return periodo;
	}
	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}
	public Date getDataInicial() {
		return dataInicial;
	}
	public void setDataInicial(Date dataInicial) {
		this.dataInicial = dataInicial;
	}
	public Date getDataFinal() {
		return dataFinal;
	}
	public void setDataFinal(Date dataFinal) {
		this.dataFinal = dataFinal;
	}
	public BigDecimal getTaxaAssociacao() {
		return taxaAssociacao;
	}
	public void setTaxaAssociacao(BigDecimal taxaAssociacao) {
		this.taxaAssociacao = taxaAssociacao;
	}
	public Integer getTentativaPeriodoDuracao() {
		return tentativaPeriodoDuracao;
	}
	public void setTentativaPeriodoDuracao(Integer tentativaPeriodoDuracao) {
		this.tentativaPeriodoDuracao = tentativaPeriodoDuracao;
	}
	public ExpiracaoDTO getExpiracao() {
		return expiracao;
	}
	public void setExpiracao(ExpiracaoDTO expiracao) {
		this.expiracao = expiracao;
	}
	public String getDiaDoAno() {
		return diaDoAno;
	}
	public void setDiaDoAno(String diaDoAno) {
		this.diaDoAno = diaDoAno;
	}
	public Integer getDiaDoMes() {
		return diaDoMes;
	}
	public void setDiaDoMes(Integer diaDoMes) {
		this.diaDoMes = diaDoMes;
	}
	public String getDiaDaSemana() {
		return diaDaSemana;
	}
	public void setDiaDaSemana(String diaDaSemana) {
		this.diaDaSemana = diaDaSemana;
	}
	public String getCancelarURL() {
		return cancelarURL;
	}
	public void setCancelarURL(String cancelarURL) {
		this.cancelarURL = cancelarURL;
	}
	  
	  
}
