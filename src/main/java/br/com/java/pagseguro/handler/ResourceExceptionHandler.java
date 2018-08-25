package br.com.java.pagseguro.handler;

import br.com.java.pagseguro.domain.vo.DetalheErro;
import br.com.java.pagseguro.util.DetalheErroBuilder;
import br.com.java.pagseguro.exception.TransacaoAbortadaException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * Created by tawan on 11/08/18.
 */
@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(TransacaoAbortadaException.class)
    public ResponseEntity<DetalheErro> handlerTransacaoAbortadaException(
                    TransacaoAbortadaException exception, HttpServletRequest request) {

        DetalheErro erro = DetalheErroBuilder.getBuilder()
                .withTitulo("Erro ao realizar Transação")
                .withStatus(500L)
                .withData(new Date())
                .withMensagemDesenvolvedor(exception.getMessage() + " ##### " + request.getRequestURI())
                .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
    }
}
