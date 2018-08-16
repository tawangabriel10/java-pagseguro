package br.com.java.pagseguro.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.java.pagseguro.domain.PagamentoDTO;
import br.com.java.pagseguro.domain.ProdutoDTO;
import br.com.java.pagseguro.domain.RemessaDTO;
import br.com.java.pagseguro.domain.RemetenteDTO;
import br.com.java.pagseguro.util.ConstanteUtil;
import br.com.uol.pagseguro.api.common.domain.builder.AddressBuilder;
import br.com.uol.pagseguro.api.common.domain.builder.PaymentItemBuilder;
import br.com.uol.pagseguro.api.common.domain.builder.PhoneBuilder;
import br.com.uol.pagseguro.api.common.domain.builder.SenderBuilder;
import br.com.uol.pagseguro.api.common.domain.builder.ShippingBuilder;
import br.com.uol.pagseguro.api.transaction.register.DirectPaymentRegistrationBuilder;

@Service
public class PagamentoService {

	 
    public DirectPaymentRegistrationBuilder getDirectPaymentRegistrationBuilder(PagamentoDTO pagamentoDTO) {

		RemetenteDTO remetente = pagamentoDTO.getRemetente();
		RemessaDTO remessa = pagamentoDTO.getRemessa();
		
		DirectPaymentRegistrationBuilder directPayment = new DirectPaymentRegistrationBuilder()
	              .withPaymentMode("default")
	              .withCurrency(pagamentoDTO.getMoeda())
	              .withExtraAmount(pagamentoDTO.getPrecoExtra())
	              .withNotificationURL(ConstanteUtil.URL_NOTIFICATION)
	              .withReference(ConstanteUtil.REFERENCE_LIBJAVA)
	              .withSender(new SenderBuilder()
	                  .withEmail(remetente.getEmail())
	                  .withName(remetente.getNome())
	                  .withCPF(remetente.getCpf())
	                  /*
	                   * Para saber como obter o valor do Hash, acesse:
	                   * https://devs.pagseguro.uol.com.br/docs/checkout-web-usando-a-sua-tela#obter-identificacao-do-comprador
	                   */
	                  .withHash("abc123")
	                  .withPhone(new PhoneBuilder()
	                      .withAreaCode(remetente.getTelefone().getCodigoArea())
	                      .withNumber(remetente.getTelefone().getNumero())))
	              .withShipping(new ShippingBuilder()
	                  .withType(remessa.getTipo())
	                  .withCost(remessa.getCusto())
	                  .withAddress(new AddressBuilder()
	                      .withPostalCode(remessa.getEndereco().getCodigoPostal())
	                      .withCountry(remessa.getEndereco().getPais())
	                      .withState(remessa.getEndereco().getEstado())
	                      .withCity(remessa.getEndereco().getCidade())
	                      .withComplement(remessa.getEndereco().getComplemento())
	                      .withDistrict(remessa.getEndereco().getDistrito())
	                      .withNumber(remessa.getEndereco().getNumero())
	                      .withStreet(remessa.getEndereco().getRua())));
	          
	          
    	      List<ProdutoDTO> produtos = pagamentoDTO.getProdutos();
    	      
    	      produtos.forEach(p -> {
    	    	  directPayment.addItem(new PaymentItemBuilder()
    	                  .withId(p.getId().toString())
    	                  .withDescription(p.getDescricao())
    	                  .withAmount(p.getPreco())
    	                  .withQuantity(p.getQuantidade())
    	                  .withWeight(p.getPeso()));
    	      });
    	      
    	      return directPayment;
    }
}
