package br.com.java.pagseguro.controller;

import br.com.java.pagseguro.domain.PagamentoDTO;
import br.com.java.pagseguro.service.TransacaoService;
import br.com.java.pagseguro.util.exception.TransacaoAbortadaException;
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
@RequestMapping(value = "/transacao")
public class TransacaoRestController {

    @Autowired
    private TransacaoService transacaoService;

    @RequestMapping(value = "/boleto-bancario", method = RequestMethod.POST)
    public ResponseEntity realizarTransacaoBoletoBancario(@RequestBody PagamentoDTO pagamentoDTO) throws TransacaoAbortadaException {
        transacaoService.criarTransacaoPorBoletoBancario(pagamentoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(null);
    }
}
