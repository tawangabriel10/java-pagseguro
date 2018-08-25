package br.com.java.pagseguro.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.java.pagseguro.domain.RemetenteDTO;
import br.com.uol.pagseguro.api.common.domain.builder.SenderBuilder;

@Component
public class RemetenteComponent {

	@Autowired
	private TelefoneComponent telefoneComponent;
	
	public SenderBuilder toSenderBuilder(RemetenteDTO remetente) {
		return new  SenderBuilder()
				.withEmail(remetente.getEmail())
				.withName(remetente.getNome())
				.withCPF(remetente.getCpf())
				/*
                 * Para saber como obter o valor do Hash, acesse:
                 * https://devs.pagseguro.uol.com.br/docs/checkout-web-usando-a-sua-tela#obter-identificacao-do-comprador
                 */
				.withHash("abc123")
				.withPhone(
						telefoneComponent.toPhoneBuilder(remetente.getTelefone()));
	}

}
