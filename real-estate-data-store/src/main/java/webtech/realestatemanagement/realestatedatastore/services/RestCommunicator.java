package webtech.realestatemanagement.realestatedatastore.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class RestCommunicator {

    private RestTemplate restTemplate;
    private ObjectMapper objectMapper;

    public RestCommunicator(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
        this.objectMapper = new ObjectMapper();
    }

    public String sendPostRequest(String url, Object body, String authToken) throws Exception {
        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(authToken.split(" ")[1]);
        HttpEntity<String> request = new HttpEntity<>(body != null ? objectMapper.writeValueAsString(body) : null, headers);
        final ResponseEntity<String> responseEntity = restTemplate.postForEntity(url, request, String.class);
        if (!HttpStatus.OK.equals(responseEntity.getStatusCode())) {
            throw new IllegalStateException();
        }
        return request.getBody();
    }

    public void sendPutRequest(String url, Object body) throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<>(objectMapper.writeValueAsString(body), headers);
        restTemplate.put(url, request, String.class);
    }

}
