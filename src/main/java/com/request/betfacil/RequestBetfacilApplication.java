package com.request.betfacil;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.request.betfacil.service.RequestBetfacilService;
import com.request.betfacil.util.RequestUtils;

@SpringBootApplication
public class RequestBetfacilApplication {
	
	private final Logger LOGGER = LogManager.getLogger(RequestBetfacilApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(RequestBetfacilApplication.class, args);
	}
	
	 @Bean
	    CommandLineRunner lookup(RequestBetfacilService service) {
	        return args -> {
	            
	            try {
	            	service.testRequestBetFacilRemote();
	            } catch (RuntimeException e) {
	                LOGGER.error(e.getMessage(), e);
	            }
	        };
	    }
}
