package br.com.java.pagseguro.util;

import br.com.java.pagseguro.domain.vo.DetalheErro;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by tawan on 11/08/18.
 */
public final class DetalheErroBuilder {

    private DetalheErro detalheErro;

    private DetalheErroBuilder() {
        detalheErro = new DetalheErro();
    }

    public static DetalheErroBuilder getBuilder() {
        return new DetalheErroBuilder();
    }

    public DetalheErroBuilder withTitulo(String titulo) {
        detalheErro.setTitulo(titulo);

        return this;
    }

    public DetalheErroBuilder withStatus(Long status) {
        detalheErro.setStatus(status);

        return this;
    }

    public DetalheErroBuilder withData(Date data) {
        detalheErro.setData(data);

        return this;
    }

    public DetalheErroBuilder withMensagemDesenvolvedor(String mensagemDesenvolvedor) {
        detalheErro.setMensagemDesenvolvedor(mensagemDesenvolvedor);

        return this;
    }

    public DetalheErro build() {
        return detalheErro;
    }

}
