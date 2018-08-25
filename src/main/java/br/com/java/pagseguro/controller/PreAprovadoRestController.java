package br.com.java.pagseguro.controller;

import br.com.java.pagseguro.exception.TransacaoAbortadaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.java.pagseguro.domain.PagamentoDTO;
import br.com.java.pagseguro.service.CheckoutService;

@RestController
@RequestMapping(value = "/pre-aprovado", produces = MediaType.APPLICATION_JSON_VALUE)
public class PreAprovadoRestController {


}
