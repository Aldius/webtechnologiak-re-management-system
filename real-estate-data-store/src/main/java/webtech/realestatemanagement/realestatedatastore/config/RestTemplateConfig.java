package webtech.realestatemanagement.realestatedatastore.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import webtech.realestatemanagement.realestatedatastore.services.RestCommunicator;

@Configuration
public class RestTemplateConfig {

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() { return new RestTemplate(); }

    @Bean
    public RestCommunicator restCommunicator() {
        return new RestCommunicator(restTemplate());
    }
}
