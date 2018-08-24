package br.com.java.pagseguro.exception;

/**
 * Created by tawan on 11/08/18.
 */
public class TransacaoAbortadaException extends RuntimeException {

    public TransacaoAbortadaException(String mensagem) {
        super(mensagem);
    }

    public TransacaoAbortadaException(String mensagem, Throwable causa) {
        super(mensagem, causa);
    }
}
