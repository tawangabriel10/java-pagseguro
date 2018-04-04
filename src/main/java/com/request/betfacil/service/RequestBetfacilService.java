package com.request.betfacil.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import com.request.betfacil.util.RequestUtils;

@Service
public class RequestBetfacilService {
	
	@Autowired
	private RequestUtils request;
	
	public void testRequestBetFacilRemote() {
		HttpHeaders headers = headersUtils.getHeadersDefaults();
        headers.setAccept(Arrays.asList(
                MediaType.APPLICATION_JSON_UTF8));
        restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<>(headers), clazz)

	}

}
