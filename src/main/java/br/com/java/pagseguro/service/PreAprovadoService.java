package br.com.java.pagseguro.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.DatatypeConverter;

import br.com.java.pagseguro.component.PagamentoComponent;
import br.com.java.pagseguro.domain.PagamentoDTO;
import br.com.java.pagseguro.exception.TransacaoAbortadaException;
import br.com.uol.pagseguro.api.PagSeguro;
import br.com.uol.pagseguro.api.common.domain.DataList;
import br.com.uol.pagseguro.api.common.domain.builder.DateRangeBuilder;
import br.com.uol.pagseguro.api.preapproval.RegisteredPreApproval;
import br.com.uol.pagseguro.api.preapproval.cancel.CancelledPreApproval;
import br.com.uol.pagseguro.api.preapproval.cancel.PreApprovalCancellationBuilder;

@Service
public class PreAprovadoService {

	@Autowired
	private PagSeguro pagSeguro;
	
	@Autowired
	private PagamentoComponent pagamentoComponent;
	
	/**
	 * 
	 * @return
	 * @throws TransacaoAbortadaException
	 */
	public DataList buscarPreAprovados() throws TransacaoAbortadaException {
	    try{
	        DataList dataList = pagSeguro.preApprovals().search().byDateRange(
	            new DateRangeBuilder().between(
	                DatatypeConverter.parseDateTime("2016-10-01T00:00:00.000-03:00").getTime(),
	                DatatypeConverter.parseDateTime("2016-10-03T15:56:00.000-03:00").getTime()),
	            1,
	            10
	        );
	        System.out.println(dataList);
	        return dataList;
	      }catch (Exception e){
	        e.printStackTrace();
	        throw new TransacaoAbortadaException(e.getMessage(), e.getCause());
	      }
	}
	
	/**
	 * 
	 * @param pagamento
	 */
	public void criarPreAprovado(PagamentoDTO pagamento) throws TransacaoAbortadaException {

		try {
			//Assinatura
			RegisteredPreApproval registeredPreApproval = pagSeguro.preApprovals().register(
	    		  pagamentoComponent.getPreApprovalRegistrationBuilder(pagamento));
			
			System.out.println(registeredPreApproval.getRedirectURL());
	    } catch (Exception e){
	      e.printStackTrace();
	      throw new TransacaoAbortadaException(e.getMessage(), e.getCause());
	    }
	}
	
/**
 * 
 * @param codigo
 * @throws TransacaoAbortadaException
 */
	public void cancelarPreAprovado(String codigo) throws TransacaoAbortadaException {
		   try{

			   //Cancelamento de assinaturas
			   CancelledPreApproval cancelledPreApproval = pagSeguro.preApprovals().cancel(
					   new PreApprovalCancellationBuilder().withCode("F50E50A8B5B5743AA4E67F8D78D11A62"));
			   System.out.println(cancelledPreApproval.getTransactionStatus());

		   } catch (Exception e){
			   e.printStackTrace();
			   throw new TransacaoAbortadaException(e.getMessage(), e.getCause());
		   }
	}
	
}
