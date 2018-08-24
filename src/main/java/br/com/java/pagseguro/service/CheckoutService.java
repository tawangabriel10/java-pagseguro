package br.com.java.pagseguro.service;

import java.math.BigDecimal;
import java.util.List;

import br.com.java.pagseguro.exception.TransacaoAbortadaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.java.pagseguro.component.ProdutoComponent;
import br.com.java.pagseguro.component.RemessaComponent;
import br.com.java.pagseguro.component.RemetenteComponent;
import br.com.java.pagseguro.domain.PagamentoDTO;
import br.com.java.pagseguro.domain.ProdutoDTO;
import br.com.uol.pagseguro.api.PagSeguro;
import br.com.uol.pagseguro.api.checkout.CheckoutRegistrationBuilder;
import br.com.uol.pagseguro.api.checkout.RegisteredCheckout;
import br.com.uol.pagseguro.api.common.domain.builder.AcceptedPaymentMethodsBuilder;
import br.com.uol.pagseguro.api.common.domain.builder.ConfigBuilder;
import br.com.uol.pagseguro.api.common.domain.builder.PaymentMethodBuilder;
import br.com.uol.pagseguro.api.common.domain.builder.PaymentMethodConfigBuilder;
import br.com.uol.pagseguro.api.common.domain.enums.ConfigKey;
import br.com.uol.pagseguro.api.common.domain.enums.PaymentMethodGroup;

@Service
public class CheckoutService {
	
	@Autowired
	private PagSeguro pagSeguro;
	
	@Autowired
	private RemetenteComponent remetenteComponent;
	
	@Autowired
	private RemessaComponent remessaComponent;
	
	@Autowired
	private ProdutoComponent produtoComponent;
	
	public void checkoutRegister(PagamentoDTO pagamento) throws TransacaoAbortadaException {

        try {

            //Criando um checkout
            
        	CheckoutRegistrationBuilder checkoutRegisterBuilder = new CheckoutRegistrationBuilder()
                    .withCurrency(pagamento.getMoeda())
                    .withExtraAmount(pagamento.getPrecoExtra())
                    .withReference(pagamento.getReferencia())
                    .withSender(
                    		remetenteComponent.toSenderBuilder(pagamento.getRemetente()))
                    .withShipping(
                    		remessaComponent.toShippingBuilder(pagamento.getRemessa()));
   

                    //Para definir o a inclusão ou exclusão de um meio você deverá utilizar três parâmetros: o parâmetro que define a configuração do grupo,
                    // o grupo de meios de pagamento e o nome do meio de pagamento.
                    // No parâmetro que define a configuração do grupo você informará se o grupo ou o meio de pagamento será incluído ou excluído.
                    // Já no grupo você informará qual o grupo de meio de pagamento que receberá a configuração definida anteriormente.
                    // Você poderá incluir e excluir os grupos de meios de pagamento Boleto, Débito, Cartão de Crédito, Depósito Bancário e Saldo PagSeguro.
                    // Já no parâmetro nome você informará qual o meio de pagamento que receberá a configuração definida anteriormente. Os meios são as bandeiras e bancos disponíveis.
                    //Atenção:  - Não é possível incluir e excluir o mesmo grupo de meio de pagamento (Ex.: incluir e excluir o grupo CREDIT_CARD).
                    // - Não é possível incluir um grupo e um meio do mesmo grupo (Ex.: incluir grupo cartão e bandeira visa na mesma chamada);
                    // - Não é possível excluir um grupo e um meio do mesmo grupo (Ex.: excluir grupo cartão e bandeira visa na mesma chamada);
                    // - Não é possível incluir e excluir o mesmo meio de pagamento (Ex.: incluir e excluir a bandeira VISA).

        	checkoutRegisterBuilder.withAcceptedPaymentMethods(new AcceptedPaymentMethodsBuilder()
                        .addInclude(new PaymentMethodBuilder()
                            .withGroup(PaymentMethodGroup.BALANCE)
                        )
                        .addInclude(new PaymentMethodBuilder()
                            .withGroup(PaymentMethodGroup.BANK_SLIP)
                        )
                        .addInclude(new PaymentMethodBuilder()
                            .withGroup(PaymentMethodGroup.CREDIT_CARD)
                        )
                        .addInclude(new PaymentMethodBuilder()
                            .withGroup(PaymentMethodGroup.DEPOSIT)
                        )
                        .addInclude(new PaymentMethodBuilder()
                            .withGroup(PaymentMethodGroup.ONLINE_DEBIT))
                    )

                    //Para definir o percentual de desconto para um meio de pagamento você deverá utilizar três parâmetros: grupo de meios de pagamento, chave configuração de desconto e o percentual de desconto. No parâmetro de grupo você deve informar um dos meios de pagamento disponibilizados pelo PagSeguro. Após definir o grupo é necessário definir os a configuração dos campos chave e valor.
                    .addPaymentMethodConfig(new PaymentMethodConfigBuilder()
                        .withPaymentMethod(new PaymentMethodBuilder()
                            .withGroup(PaymentMethodGroup.CREDIT_CARD)
                        )
                        .withConfig(new ConfigBuilder()
                            .withKey(ConfigKey.DISCOUNT_PERCENT)
                            .withValue(new BigDecimal(10.00))
                        )
                    )
                    .addPaymentMethodConfig(new PaymentMethodConfigBuilder()
                        .withPaymentMethod(new PaymentMethodBuilder()
                            .withGroup(PaymentMethodGroup.BANK_SLIP)
                        )
                        .withConfig(new ConfigBuilder()
                            .withKey(ConfigKey.DISCOUNT_PERCENT)
                            .withValue(new BigDecimal(10.00))
                        )
                    )

                    //Para definir o parcelamento você deverá utilizar três parâmetros: grupo, chave e valor.
                    // No parâmetro grupo você informará qual o meio pagamento que receberá as configurações.
                    // Para limitar o parcelamento você deve informar o meio de pagamento Cartão de crédito (CREDIT_CARD).
                    //Após definir o grupo você deverá definir as configurações nos campos chave e valor.
                    // Estes devem ser definidos com a chave MAX_INSTALLMENTS_LIMIT que define a configuração de limite de parcelamento e como valor o número de parcelas que você deseja apresentar ao cliente (de 2 a 18 parcelas).

                    .addPaymentMethodConfig(new PaymentMethodConfigBuilder()
                        .withPaymentMethod(new PaymentMethodBuilder()
                            .withGroup(PaymentMethodGroup.CREDIT_CARD)
                        )
                        .withConfig(new ConfigBuilder()
                            .withKey(ConfigKey.MAX_INSTALLMENTS_LIMIT)
                            .withValue(new BigDecimal(10))
                        )
                    )
                    .addPaymentMethodConfig(new PaymentMethodConfigBuilder()
                        .withPaymentMethod(new PaymentMethodBuilder()
                            .withGroup(PaymentMethodGroup.CREDIT_CARD)
                        )
                        .withConfig(new ConfigBuilder()
                            .withKey(ConfigKey.MAX_INSTALLMENTS_NO_INTEREST)
                            .withValue(new BigDecimal(5))
                        )
                    );
        	

        	List<ProdutoDTO> produtos = pagamento.getProdutos();
        	
        	produtos.forEach(p -> {
        		checkoutRegisterBuilder.addItem(
        				produtoComponent.toPaymentItemBuilder(p));
        	});
                    
            RegisteredCheckout registeredCheckout = pagSeguro.checkouts().register(checkoutRegisterBuilder);
            System.out.println(registeredCheckout.getRedirectURL());

        } catch (Exception e){
            e.printStackTrace();
            throw new TransacaoAbortadaException(e.getMessage(), e.getCause());
        }
	}
	

}
