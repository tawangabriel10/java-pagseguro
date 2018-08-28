package br.com.java.pagseguro.controller;

import br.com.java.pagseguro.exception.TransacaoAbortadaException;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.java.pagseguro.domain.PagamentoDTO;
import br.com.java.pagseguro.service.CheckoutService;
import br.com.java.pagseguro.service.PreAprovadoService;
import br.com.uol.pagseguro.api.common.domain.DataList;
import br.com.uol.pagseguro.api.preapproval.search.PreApprovalSummary;

@RestController
@RequestMapping(value = "/pre-aprovado", produces = MediaType.APPLICATION_JSON_VALUE)
public class PreAprovadoRestController {

	@Autowired
	private PreAprovadoService preAprovadoService;
	
	@GetMapping(value = "/datas")
	public ResponseEntity<DataList<? extends PreApprovalSummary>> buscarPreAprovadosPorDatas(
				@RequestParam(value = "dataInicial", required = true) @DateTimeFormat(pattern="yyyy-MM-dd") Date dataInicial,
				@RequestParam(value = "dataFinal", required = true) @DateTimeFormat(pattern="yyyy-MM-dd") Date dataFinal
			) throws TransacaoAbortadaException {
		DataList<? extends PreApprovalSummary> preAprovados = preAprovadoService.buscarPreAprovados(dataInicial, dataFinal);
		return ResponseEntity.status(HttpStatus.OK).body(preAprovados);
	}

}
