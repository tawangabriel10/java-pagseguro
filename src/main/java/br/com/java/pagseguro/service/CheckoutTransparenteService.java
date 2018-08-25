package br.com.java.pagseguro.service;

import br.com.java.pagseguro.component.CartaoCreditoComponent;
import br.com.java.pagseguro.component.PagamentoComponent;
import br.com.java.pagseguro.domain.CartaoCreditoDTO;
import br.com.java.pagseguro.domain.PagamentoDTO;
import br.com.java.pagseguro.exception.TransacaoAbortadaException;
import br.com.uol.pagseguro.api.PagSeguro;
import br.com.uol.pagseguro.api.common.domain.builder.BankBuilder;
import br.com.uol.pagseguro.api.common.domain.builder.DateRangeBuilder;
import br.com.uol.pagseguro.api.transaction.register.DirectPaymentRegistrationBuilder;
import br.com.uol.pagseguro.api.transaction.search.TransactionDetail;
import br.com.uol.pagseguro.api.transaction.search.TransactionSummary;
import br.com.uol.pagseguro.api.common.domain.BankName;
import br.com.uol.pagseguro.api.common.domain.DataList;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by tawan on 11/08/18.
 */
@Service
public class CheckoutTransparenteService {

    @Autowired
    private PagSeguro pagSeguro;
    
    @Autowired
    private PagamentoComponent pagamentoComponent;
    
    @Autowired
    private CartaoCreditoComponent cartaoCreditoComponent;
    /**
     * 
     * @param dataInicial
     * @param dataFinal
     */
    public void buscarTransacoesPorDatas(Date dataInicial, Date dataFinal) {
        try {
    		
            final int pagina = 1;
            final int maxResultados = 10;
    	      
            final DataList<? extends TransactionSummary> transactions =
                    pagSeguro.transactions().search().byDateRange(
                            new DateRangeBuilder().between(dataInicial, dataFinal), pagina, maxResultados);
            System.out.println(transactions.size());
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    
    /**
     * 
     * @param codigoTransacao
     */
    public void buscarTransacaoPorCodigo(String codigoTransacao) {
        try {
            TransactionDetail transaction = pagSeguro.transactions().search().byCode(codigoTransacao);
            System.out.println(transaction);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    
    /**
     * 
     * @param codigoNotificacao
     */
    public void buscarTransacaoPorCodigoNotificacao(String codigoNotificacao) {
        try {
            TransactionDetail transaction = pagSeguro.transactions().search().byNotificationCode(codigoNotificacao);
            System.out.println(transaction);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 
     * @param pagamentoDTO
     * @throws TransacaoAbortadaException
     */
    public void criarTransacaoTransparentePorCartaoDeCredito(PagamentoDTO pagamentoDTO) throws TransacaoAbortadaException {
        try{
            DirectPaymentRegistrationBuilder directPayment = pagamentoComponent.toDirectPaymentRegistrationBuilder(pagamentoDTO);
            // Checkout transparente (pagamento direto) com cartao de credito
            TransactionDetail creditCardTransaction = pagSeguro.transactions()
                    .register(directPayment)
                    .withCreditCard(
                            cartaoCreditoComponent.toCreditCardBuilder(pagamentoDTO.getCartaoCredito()));
            System.out.println(creditCardTransaction);

        } catch (Exception e){
            e.printStackTrace();
            throw new TransacaoAbortadaException(e.getMessage(), e.getCause());
        }
    }

    /**
     * 
     * @param pagamentoDTO
     * @throws TransacaoAbortadaException
     */
    public void criarTransacaoTransparentePorBoletoBancario(PagamentoDTO pagamentoDTO) throws TransacaoAbortadaException {
        try{
    		
            DirectPaymentRegistrationBuilder directPayment = pagamentoComponent.toDirectPaymentRegistrationBuilder(pagamentoDTO);
            // Checkout transparente (pagamento direto) com boleto
            TransactionDetail bankSlipTransaction =
                    pagSeguro.transactions().register(directPayment).withBankSlip();
	      
            System.out.println(bankSlipTransaction);
        } catch (Exception e) {
            e.printStackTrace();
            throw new TransacaoAbortadaException(e.getMessage(), e.getCause());
        }
    }

    /**
     * 
     * @param pagamentoDTO
     * @throws TransacaoAbortadaException
     */
    public void criarTransacaoTransparentePorDebitoOnline(PagamentoDTO pagamentoDTO) throws TransacaoAbortadaException {
        try{
            DirectPaymentRegistrationBuilder directPayment = pagamentoComponent.toDirectPaymentRegistrationBuilder(pagamentoDTO);
            //Checkout transparente (pagamento direto) com debito online
            TransactionDetail onlineDebitTransaction =
                    pagSeguro.transactions().register(directPayment).withOnlineDebit(new BankBuilder()
                            .withName(BankName.Name.BANCO_DO_BRASIL)
                    );
            System.out.println(onlineDebitTransaction);
        }catch (Exception e){
            e.printStackTrace();
            throw new TransacaoAbortadaException(e.getMessage(), e.getCause());
        }
    }

}
