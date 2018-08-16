package br.com.java.pagseguro.service;

import br.com.java.pagseguro.domain.CartaoCreditoDTO;
import br.com.java.pagseguro.domain.DocumentoTitularDTO;
import br.com.java.pagseguro.domain.PagamentoDTO;
import br.com.java.pagseguro.domain.ProdutoDTO;
import br.com.java.pagseguro.domain.RemessaDTO;
import br.com.java.pagseguro.domain.RemetenteDTO;
import br.com.java.pagseguro.util.ConstanteUtil;
import br.com.java.pagseguro.util.exception.TransacaoAbortadaException;
import br.com.uol.pagseguro.api.PagSeguro;
import br.com.uol.pagseguro.api.common.domain.builder.AddressBuilder;
import br.com.uol.pagseguro.api.common.domain.builder.BankBuilder;
import br.com.uol.pagseguro.api.common.domain.builder.CreditCardBuilder;
import br.com.uol.pagseguro.api.common.domain.builder.DateRangeBuilder;
import br.com.uol.pagseguro.api.common.domain.builder.DocumentBuilder;
import br.com.uol.pagseguro.api.common.domain.builder.HolderBuilder;
import br.com.uol.pagseguro.api.common.domain.builder.InstallmentBuilder;
import br.com.uol.pagseguro.api.common.domain.builder.PaymentItemBuilder;
import br.com.uol.pagseguro.api.common.domain.builder.PhoneBuilder;
import br.com.uol.pagseguro.api.common.domain.builder.SenderBuilder;
import br.com.uol.pagseguro.api.common.domain.builder.ShippingBuilder;
import br.com.uol.pagseguro.api.common.domain.enums.DocumentType;
import br.com.uol.pagseguro.api.common.domain.enums.State;
import br.com.uol.pagseguro.api.transaction.register.DirectPaymentRegistrationBuilder;
import br.com.uol.pagseguro.api.transaction.search.TransactionDetail;
import br.com.uol.pagseguro.api.transaction.search.TransactionSummary;
import br.com.uol.pagseguro.api.common.domain.BankName;
import br.com.uol.pagseguro.api.common.domain.DataList;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by tawan on 11/08/18.
 */
@Service
public class TransacaoService {

    @Autowired
    private PagSeguro pagSeguro;
    
    @Autowired
    private PagamentoService pagamentoService;
    
    public void buscarTransacoesPorDatas(Date dataInicial, Date dataFinal) {
    	try {
    		
    		final int pagina = 1;
    		final int maxResultados = 10;
    	      
    		final DataList<? extends TransactionSummary> transactions =
    	          pagSeguro.transactions().search().byDateRange(new DateRangeBuilder().between(dataInicial, dataFinal), pagina, maxResultados);
    		System.out.println(transactions.size());
	    }catch (Exception e){
	      e.printStackTrace();
	    }
    }
    
    public void buscarTransacaoPorCodigo(String codigoTransacao) {
    	try {
    		TransactionDetail transaction = pagSeguro.transactions().search().byCode(codigoTransacao);
    		System.out.println(transaction);
	    }catch (Exception e){
	      e.printStackTrace();
	    }
    }
    
    public void buscarTransacaoPorCodigoNotificacao(String codigoNotificacao) {
    	 try {
		      TransactionDetail transaction = pagSeguro.transactions().search().byNotificationCode(codigoNotificacao);
		      System.out.println(transaction);
	    }catch (Exception e){
	      e.printStackTrace();
	    }
    }

    public void criarTransacaoPorCartaoDeCredito(PagamentoDTO pagamentoDTO) throws TransacaoAbortadaException {
    	 try{
    		 CartaoCreditoDTO cartaoCredito = pagamentoDTO.getCartaoCredito();
    		 List<DocumentoTitularDTO> documentos = pagamentoDTO.getCartaoCredito().getTitular().getDocumentos();
    		 
    		 HolderBuilder holder = new HolderBuilder()
             .withName(cartaoCredito.getTitular().getNome())
             .withBithDate(new SimpleDateFormat("dd/MM/yyyy").parse(cartaoCredito.getTitular().getDataAniversario().toString()))
             .withPhone(new PhoneBuilder()
                 .withAreaCode(cartaoCredito.getTitular().getTelefone().getCodigoArea())
                 .withNumber(cartaoCredito.getTitular().getTelefone().getNumero()));
             
             documentos.forEach(d -> {
            	 holder.addDocument(new DocumentBuilder()
                         .withType(d.getTipo())
                         .withValue(d.getValor()));
             });

     		DirectPaymentRegistrationBuilder directPayment = pagamentoService.getDirectPaymentRegistrationBuilder(pagamentoDTO);
             // Checkout transparente (pagamento direto) com cartao de credito
             TransactionDetail creditCardTransaction = pagSeguro.transactions()
        		 .register(directPayment)
        		 .withCreditCard(new CreditCardBuilder()
                     .withBillingAddress(new AddressBuilder()
                         .withPostalCode(cartaoCredito.getEnderecoCobranca().getCodigoPostal())
                         .withCountry(cartaoCredito.getEnderecoCobranca().getPais())
                         .withState(cartaoCredito.getEnderecoCobranca().getEstado())
                         .withCity(cartaoCredito.getEnderecoCobranca().getCidade())
                         .withComplement(cartaoCredito.getEnderecoCobranca().getComplemento())
                         .withDistrict(cartaoCredito.getEnderecoCobranca().getDistrito())
                         .withNumber(cartaoCredito.getEnderecoCobranca().getNumero())
                         .withStreet(cartaoCredito.getEnderecoCobranca().getRua())
                     )
                     .withInstallment(new InstallmentBuilder()
                         .withQuantity(cartaoCredito.getPrestacao().getQuantidade())
                         .withValue(cartaoCredito.getPrestacao().getValor())
                     )
                     .withHolder(holder)
                     .withToken("token")
                 );
             System.out.println(creditCardTransaction);

         }catch (Exception e){
             e.printStackTrace();
         }
    }

    public void criarTransacaoPorBoletoBancario(PagamentoDTO pagamentoDTO) throws TransacaoAbortadaException {
    	try{
    		
    		DirectPaymentRegistrationBuilder directPayment = pagamentoService.getDirectPaymentRegistrationBuilder(pagamentoDTO);
    		// Checkout transparente (pagamento direto) com boleto
    		TransactionDetail bankSlipTransaction =
	          pagSeguro.transactions().register(directPayment).withBankSlip();
	      
    		System.out.println(bankSlipTransaction);
    	  } catch (Exception e) {
              e.printStackTrace();
              throw new TransacaoAbortadaException(e.getMessage(), e.getCause());
          }
    }

    public void criarTransacaoPorDebutoOnliue(PagamentoDTO pagamentoDTO) throws TransacaoAbortadaException {
    	   try{
       			DirectPaymentRegistrationBuilder directPayment = pagamentoService.getDirectPaymentRegistrationBuilder(pagamentoDTO);
               //Checkout transparente (pagamento direto) com debito online
               TransactionDetail onlineDebitTransaction =
                   pagSeguro.transactions().register(directPayment).withOnlineDebit(new BankBuilder()
                       .withName(BankName.Name.BANCO_DO_BRASIL)
                   );
               System.out.println(onlineDebitTransaction);
           }catch (Exception e){
               e.printStackTrace();
           }
    }
   
}
