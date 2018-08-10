package br.com.java.pagseguro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.java.pagseguro.domain.PagamentoDTO;
import br.com.java.pagseguro.domain.ProdutoDTO;
import br.com.java.pagseguro.service.PagamentoService;

@RestController
@RequestMapping(value = "/produtos")
public class PagamentoRestController {
	
	@Autowired
	private PagamentoService pagamentoService;
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ResponseEntity<Void> registrarCheckout(@RequestBody PagamentoDTO pagamento) {
		pagamentoService.checkoutRegister(pagamento);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	

}
