package br.com.java.pagseguro.service;

import br.com.java.pagseguro.domain.PagamentoDTO;
import br.com.uol.pagseguro.api.PagSeguro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by tawan on 11/08/18.
 */
@Service
public class TransacaoService {

    @Autowired
    private PagSeguro pagSeguro;

    public void criarTransacaoPorCartaoDeCredito(PagamentoDTO pagamentoDTO) {

    }

    public void criarTransacaoPorBoletoBancario(PagamentoDTO pagamentoDTO) {

    }

    public void criarTransacaoPorDebutoEmConta(PagamentoDTO pagamentoDTO) {

    }
}
