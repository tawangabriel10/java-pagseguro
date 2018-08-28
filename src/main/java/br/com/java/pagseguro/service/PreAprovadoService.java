package br.com.java.pagseguro.service;

import br.com.java.pagseguro.component.PreAprovadoComponent;
import br.com.java.pagseguro.domain.PreAprovadoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

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
import br.com.uol.pagseguro.api.preapproval.search.PreApprovalSummary;

@Service
public class PreAprovadoService {

	@Autowired
	private PagSeguro pagSeguro;
	
	@Autowired
	private PreAprovadoComponent preAprovadoComponent;
	
	/**
	 * 
	 * @param dataInicial
	 * @param dataFinal
	 * @return
	 * @throws TransacaoAbortadaException
	 */
	public DataList<? extends PreApprovalSummary> buscarPreAprovados(Date dataInicial, Date dataFinal) throws TransacaoAbortadaException {
		try{
			DataList<? extends PreApprovalSummary> dataList = pagSeguro.preApprovals().search().byDateRange(
					new DateRangeBuilder().between(dataInicial, dataFinal), 1, 10);
			System.out.println(dataList);
			return dataList;
		}catch (Exception e){
			e.printStackTrace();
			throw new TransacaoAbortadaException(e.getMessage(), e.getCause());
		}
	}
	
	/**
	 * 
	 * @param preAprovado
	 */
	public void criarPreAprovado(PreAprovadoDTO preAprovado) throws TransacaoAbortadaException {

		try {
			//Assinatura
			RegisteredPreApproval registeredPreApproval = pagSeguro.preApprovals().register(
					preAprovadoComponent.toPreApprovalRegistrationBuilder(preAprovado));
			
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
	public String cancelarPreAprovado(String codigo) throws TransacaoAbortadaException {
		try{

			//Cancelamento de assinaturas
			CancelledPreApproval cancelledPreApproval = pagSeguro.preApprovals().cancel(
				new PreApprovalCancellationBuilder().withCode("F50E50A8B5B5743AA4E67F8D78D11A62"));
			System.out.println(cancelledPreApproval.getTransactionStatus());
			return cancelledPreApproval.getTransactionStatus();
		} catch (Exception e){
			e.printStackTrace();
			throw new TransacaoAbortadaException(e.getMessage(), e.getCause());
		}
	}

}
