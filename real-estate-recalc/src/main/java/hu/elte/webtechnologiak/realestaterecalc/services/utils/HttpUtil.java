package hu.elte.webtechnologiak.realestaterecalc.services.utils;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class HttpUtil {

	private final RestTemplate restTemplate;

	public HttpUtil( final RestTemplate restTemplate ) {
		this.restTemplate = restTemplate;
	}

	public String sendPostRequest( final String url, final String body, final String token ) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setBearerAuth(token.split(" ")[1]);
		HttpEntity<String> request = new HttpEntity<>(body, headers);
		final ResponseEntity<String> responseEntity = restTemplate.postForEntity(url, request, String.class);
		if (!HttpStatus.OK.equals(responseEntity.getStatusCode())) {
			throw new IllegalStateException();
		}
		return request.getBody();
	}

	public String sendGetRequest( final String url ) {
		return restTemplate.getForObject(url, String.class);
	}

}
