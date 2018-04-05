package com.request.betfacil.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;

import com.request.betfacil.util.RequestUtils;

@Service
public class RequestBetfacilService {
	
	private static final String URL_BASE = "https://global987.win:8443";
	
	private static final String RESOURCE = "/banca/api";
	
	private static final String ENDPOINT = "/eventodest";
	
	@Autowired
	private RequestUtils request;
	
	public void testRequestBetFacilRemote() {
		String uri = URL_BASE + RESOURCE + ENDPOINT;
		
		HttpHeaders headers = new HttpHeaders();
		/*headers.add(HttpHeaders.ACCESS_CONTROL_ALLOW_CREDENTIALS, "true");
		headers.add(HttpHeaders.ACCESS_CONTROL_ALLOW_HEADERS, "Origin, X-Requested-With, Content-Type, Accept");
		headers.add(HttpHeaders.ACCESS_CONTROL_ALLOW_METHODS, "GET, POST, PUT, DELETE, OPTIONS");
		headers.add(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, "https://global987.win:8443/");
		headers.add(HttpHeaders.ACCESS_CONTROL_MAX_AGE, "3600");
		headers.add(HttpHeaders.ACCESS_CONTROL_REQUEST_HEADERS, "*");*/
		headers.setContentType(MediaType.TEXT_PLAIN);

		Object resultado = request.getRestTemplate().exchange(uri, HttpMethod.GET, new HttpEntity<>(headers), Object.class);
		
		System.out.println(resultado);
	}

}
