package br.com.java.pagseguro.component;

import org.springframework.stereotype.Component;

import br.com.java.pagseguro.domain.PrestacaoDTO;
import br.com.uol.pagseguro.api.common.domain.builder.InstallmentBuilder;

@Component
public class PrestacaoComponent {

	public InstallmentBuilder toInstallmentBuilder(PrestacaoDTO prestacao) {
		return new InstallmentBuilder()
                .withQuantity(prestacao.getQuantidade())
                .withValue(prestacao.getValor());
	}
}
