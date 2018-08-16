package br.com.java.pagseguro.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.uol.pagseguro.api.PagSeguro;
import br.com.uol.pagseguro.api.common.domain.DataList;
import br.com.uol.pagseguro.api.installment.InstallmentDetail;
import br.com.uol.pagseguro.api.installment.InstallmentListingBuilder;

@Service
public class ParcelamentoService {
	
	@Autowired
	private PagSeguro pagSeguro;
	
	public List<InstallmentDetail> getOpcoesParcelamento() {
		List<InstallmentDetail> listaOpcoes = new ArrayList<>();
	    try{

	        //Lista as opções de parcelamento
	        DataList<? extends InstallmentDetail> installmentDetails =
	            pagSeguro.installments().list(new InstallmentListingBuilder()
	                .withCardBrand("visa")
	                .withAmount(new BigDecimal(271.00))
	                .withMaxInstallmentNoInterest(5)
	            );

	        System.out.println(installmentDetails.getData());
	        listaOpcoes = (List<InstallmentDetail>) installmentDetails.getData();
	      }catch (Exception e){
	        e.printStackTrace();
	      }
	    return listaOpcoes;
	}

}
