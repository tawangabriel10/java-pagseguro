package br.com.java.pagseguro.controller;

import br.com.java.pagseguro.domain.PagamentoDTO;
import br.com.java.pagseguro.service.CheckoutService;
import br.com.java.pagseguro.service.CheckoutTransparenteService;
import br.com.uol.pagseguro.api.common.domain.DataList;
import br.com.uol.pagseguro.api.transaction.search.TransactionDetail;
import br.com.uol.pagseguro.api.transaction.search.TransactionSummary;
import br.com.java.pagseguro.exception.TransacaoAbortadaException;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by tawan on 15/08/18.
 */
@RestController
@RequestMapping(value = "/checkout")
public class CheckoutRestController {

    @Autowired
    private CheckoutService checkoutService;

    @Autowired
    private CheckoutTransparenteService checkoutTransparenteService;
    
    @GetMapping(value = "/transacoes/datas")
    public ResponseEntity<DataList<? extends TransactionSummary>> buscarTransacoesPorDatas(
    			@RequestParam(value = "dataInicial", required = true) @DateTimeFormat(pattern="yyyy-MM-dd") Date dataInicial,
    			@RequestParam(value = "dataFinal", required = true) @DateTimeFormat(pattern="yyyy-MM-dd") Date dataFinal
    		) throws TransacaoAbortadaException {
    	
    	DataList<? extends TransactionSummary> listaTransacoes = 
    			checkoutTransparenteService.buscarTransacoesPorDatas(dataInicial, dataFinal);
    	return ResponseEntity.status(HttpStatus.OK).body(listaTransacoes);
    }
    
    @GetMapping(value = "/transacao/codigo/{codigo}")
    public ResponseEntity<TransactionDetail> buscarTransacaoPorCodigo(@PathVariable(value = "codigo") String codigo) throws TransacaoAbortadaException {
    	TransactionDetail transacao = checkoutTransparenteService.buscarTransacaoPorCodigo(codigo);
    	return ResponseEntity.status(HttpStatus.OK).body(transacao);
    }
    
    @GetMapping(value = "/transacao/codigoNotificacao/{codigoNotificacao}")
    public ResponseEntity<TransactionDetail> buscarTransacaoPorCodigoNotificacao(@PathVariable("codigoNotificacao") String codigoNotificacao) throws TransacaoAbortadaException {
    	TransactionDetail transacao = checkoutTransparenteService.buscarTransacaoPorCodigoNotificacao(codigoNotificacao);
    	return ResponseEntity.status(HttpStatus.OK).body(transacao);
    }

    @PostMapping(value = "/register")
    public ResponseEntity<Void> registrarCheckout(@RequestBody PagamentoDTO pagamento) throws TransacaoAbortadaException {
        checkoutService.checkoutRegister(pagamento);
        return ResponseEntity.status(HttpStatus.CREATED).body(null);
    }

    @PostMapping(value = "/transparente/boleto-bancario", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity realizarTransacaoTransparenteBoletoBancario(@RequestBody PagamentoDTO pagamentoDTO) throws TransacaoAbortadaException {
        checkoutTransparenteService.criarTransacaoTransparentePorBoletoBancario(pagamentoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(null);
    }

    @PostMapping(value = "/transparente/cartao-credito", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity realizarTransacaoTransparenteCartaoCredito(@RequestBody PagamentoDTO pagamentoDTO) throws TransacaoAbortadaException {
        checkoutTransparenteService.criarTransacaoTransparentePorCartaoDeCredito(pagamentoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(null);
    }

    @PostMapping(value = "/transparente/debito-online", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity realizarTransacaoTransparenteDebitoOnline(@RequestBody PagamentoDTO pagamentoDTO) throws TransacaoAbortadaException {
        checkoutTransparenteService.criarTransacaoTransparentePorDebitoOnline(pagamentoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(null);
    }

}
