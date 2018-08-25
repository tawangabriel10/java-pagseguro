package br.com.java.pagseguro.component;

import org.springframework.stereotype.Component;

import br.com.java.pagseguro.domain.ProdutoDTO;
import br.com.uol.pagseguro.api.common.domain.builder.PaymentItemBuilder;

@Component
public class ProdutoComponent {

	public ProdutoComponent() {
		
	}
	
	public static PaymentItemBuilder toPaymentItemBuilder(ProdutoDTO produto) {
		return new PaymentItemBuilder()
				.withId(produto.getId().toString())
				.withDescription(produto.getDescricao())
				.withAmount(produto.getPreco())
				.withQuantity(produto.getQuantidade())
				.withWeight(produto.getPeso());
	}
}
