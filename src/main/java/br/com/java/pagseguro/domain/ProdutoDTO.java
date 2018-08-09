package br.com.java.pagseguro.domain;

import java.io.Serializable;
import java.math.BigDecimal;

public class ProdutoDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	private String descricao;
	
	private BigDecimal preco;
	
	private Integer quantidade;
	
	private BigDecimal peso;

}
