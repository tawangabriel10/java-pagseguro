package br.com.java.pagseguro.component;

import org.springframework.stereotype.Component;

import br.com.java.pagseguro.domain.TelefoneDTO;
import br.com.uol.pagseguro.api.common.domain.builder.PhoneBuilder;

@Component
public class TelefoneComponent {
	
	public PhoneBuilder toPhoneBuilder(TelefoneDTO telefone) {
		return new PhoneBuilder()
                .withAreaCode(telefone.getCodigoArea())
                .withNumber(telefone.getNumero());
	}
}
