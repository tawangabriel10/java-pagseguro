package com.request.betfacil.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import java.net.InetSocketAddress;
import java.net.Proxy;

import javax.annotation.PostConstruct;

@Component
public final class RequestUtils {

	    private final Logger logger = LogManager.getLogger(RequestUtils.class);

	    @Autowired
	    private RestTemplate restTemplate;

	    private static final String HOST = "";

	    private static final String PORT = "8080";

	    @PostConstruct
	    public void init(){
	        int portNr = -1;
	        try {
	            portNr = Integer.parseInt(PORT);
	        } catch (NumberFormatException e) {
	            logger.error("Unable to parse the proxy port number");
	        }
	        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
	        InetSocketAddress address = new InetSocketAddress(HOST, portNr);
	        Proxy proxy = new Proxy(Proxy.Type.HTTP,address);
	        factory.setProxy(proxy);

	        restTemplate.setRequestFactory(factory);
	    }

	    public RestTemplate getRestTemplate() {
	        return restTemplate;
	    }
}
