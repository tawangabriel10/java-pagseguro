package br.com.java.pagseguro.component;

import br.com.java.pagseguro.domain.RequisicaoPreAprovadoDTO;
import br.com.uol.pagseguro.api.common.domain.builder.DateRangeBuilder;
import br.com.uol.pagseguro.api.common.domain.builder.PreApprovalRequestBuilder;
import org.springframework.stereotype.Component;

import javax.xml.bind.DatatypeConverter;
import java.math.BigDecimal;
import java.util.Date;

@Component
public class RequisicaoPreAprovadoComponent {

	public PreApprovalRequestBuilder toPreApprovalRequestBuilder(RequisicaoPreAprovadoDTO requisicao) {
		return new PreApprovalRequestBuilder()
				.withCharge("auto")
				.withName("Seguro contra roubo do Notebook Rosa")
				.withDetails("Cada dia 28 será cobrado o valor de R$100,00 referente ao seguro " +
						"contra roubo do Notebook Prata")
				.withAmountPerPayment(BigDecimal.TEN)
				.withMaxTotalAmount(new BigDecimal(200))
				.withMaxAmountPerPeriod(BigDecimal.TEN)
				.withMaxPaymentsPerPeriod(2)
				.withPeriod("monthly")
				.withDateRange(new DateRangeBuilder()
						.between(
								new Date(),
								DatatypeConverter.parseDateTime("2018-09-27T23:59:59.000-03:00")
										.getTime()));
	}
}
