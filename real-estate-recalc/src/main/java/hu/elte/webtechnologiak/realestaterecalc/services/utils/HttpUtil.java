package hu.elte.webtechnologiak.realestaterecalc.services.utils;

import org.springframework.web.client.RestTemplate;

public class HttpUtil {

	private RestTemplate restTemplate;

	public HttpUtil( final RestTemplate restTemplate ) {
		this.restTemplate = restTemplate;
	}

	public String sendGetRequest( final String url ) {
		return restTemplate.getForObject(url, String.class);
	}

}
