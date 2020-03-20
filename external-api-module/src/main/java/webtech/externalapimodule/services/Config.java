package webtech.externalapimodule.service;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.client.RestTemplate;

import webtech.externalapimodule.service.ForecastRetrieverImpl;

@Configuration
@ComponentScan(basePackageClasses = ForecastRetrieverImpl.class)
public class Config {
    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        // Do any additional configuration here
        return builder.build();
    }
}