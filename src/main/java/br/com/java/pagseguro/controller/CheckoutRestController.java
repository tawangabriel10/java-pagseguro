package br.com.java.pagseguro.controller;

import br.com.java.pagseguro.domain.PagamentoDTO;
import br.com.java.pagseguro.service.CheckoutService;
import br.com.java.pagseguro.service.CheckoutTransparenteService;
import br.com.java.pagseguro.exception.TransacaoAbortadaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<Void> registrarCheckout(@RequestBody PagamentoDTO pagamento) throws TransacaoAbortadaException {
        checkoutService.checkoutRegister(pagamento);
        return ResponseEntity.status(HttpStatus.CREATED).body(null);
    }

    @RequestMapping(value = "/transparente/boleto-bancario", method = RequestMethod.POST)
    public ResponseEntity realizarTransacaoTransparenteBoletoBancario(@RequestBody PagamentoDTO pagamentoDTO) throws TransacaoAbortadaException {
        checkoutTransparenteService.criarTransacaoTransparentePorBoletoBancario(pagamentoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(null);
    }

    @RequestMapping(value = "/transparente/cartao-credito", method = RequestMethod.POST)
    public ResponseEntity realizarTransacaoTransparenteCartaoCredito(@RequestBody PagamentoDTO pagamentoDTO) throws TransacaoAbortadaException {
        checkoutTransparenteService.criarTransacaoTransparentePorCartaoDeCredito(pagamentoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(null);
    }

    @RequestMapping(value = "/transparente/debito-online", method = RequestMethod.POST)
    public ResponseEntity realizarTransacaoTransparenteDebitoOnline(@RequestBody PagamentoDTO pagamentoDTO) throws TransacaoAbortadaException {
        checkoutTransparenteService.criarTransacaoTransparentePorDebitoOnline(pagamentoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(null);
    }

}
