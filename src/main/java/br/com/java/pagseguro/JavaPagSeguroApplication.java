package br.com.java.pagseguro;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import br.com.java.pagseguro.domain.PagamentoDTO;
import br.com.java.pagseguro.service.CheckoutService;

@SpringBootApplication
public class JavaPagSeguroApplication {
	
	private final Logger LOGGER = LogManager.getLogger(JavaPagSeguroApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(JavaPagSeguroApplication.class, args);
	}
	
	/*@Bean
    CommandLineRunner lookup(CheckoutService service) {
        return args -> {
            
            try {
            	service.checkoutRegister(new PagamentoDTO());
            } catch (RuntimeException e) {
                LOGGER.error(e.getMessage(), e);
            }
        };
    }*/
}
