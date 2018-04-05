package com.request.betfacil.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableWebMvc
public class WebConfig extends WebMvcConfigurerAdapter {

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/api/**")
		.allowedOrigins("https://global987.win:8443/banca-fe/")
		.allowedMethods("GET", "POST", "PUT", "DELETE")
			.allowedHeaders("Origin", "X-Requested-With", "Content-Type", "Accept")
		.exposedHeaders("Origin", "X-Requested-With", "Content-Type", "Accept")
		.allowCredentials(true).maxAge(3600);
	}
}
