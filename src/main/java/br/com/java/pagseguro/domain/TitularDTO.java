package br.com.java.pagseguro.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class TitularDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4200380098214192698L;

	private String nome;
	
	private Date dataAniversario;
	
	private TelefoneDTO telefone;
	
	private List<DocumentoTitularDTO> documentos;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Date getDataAniversario() {
		return dataAniversario;
	}

	public void setDataAniversario(Date dataAniversario) {
		this.dataAniversario = dataAniversario;
	}

	public TelefoneDTO getTelefone() {
		return telefone;
	}

	public void setTelefone(TelefoneDTO telefone) {
		this.telefone = telefone;
	}

	public List<DocumentoTitularDTO> getDocumentos() {
		return documentos;
	}

	public void setDocumentos(List<DocumentoTitularDTO> documentos) {
		this.documentos = documentos;
	}
	
	
}
