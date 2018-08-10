package br.com.java.pagseguro.config;

import org.springframework.context.annotation.Bean;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import br.com.uol.pagseguro.api.PagSeguro;
import br.com.uol.pagseguro.api.PagSeguroEnv;
import br.com.uol.pagseguro.api.credential.Credential;
import br.com.uol.pagseguro.api.http.JSEHttpClient;
import br.com.uol.pagseguro.api.utils.logging.SimpleLoggerFactory;


@Configuration
@EnableWebMvc
public class WebConfig {
	
	private final static String sellerEmail = "tawan.gabriel10@gmail.com";
    private final static String sellerToken = "ACE7A350820D4B4997F0E33526D2B34A";
	
	@Bean
	public PagSeguro getPagSeguro() {
		return PagSeguro
                .instance(new SimpleLoggerFactory(), new JSEHttpClient(),
                    Credential.sellerCredential(sellerEmail, sellerToken), PagSeguroEnv.SANDBOX);
	}
}
