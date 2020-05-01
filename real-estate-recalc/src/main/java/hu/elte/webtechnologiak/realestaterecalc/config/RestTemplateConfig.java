package hu.elte.webtechnologiak.realestaterecalc.config;

import hu.elte.webtechnologiak.realestaterecalc.services.utils.HttpUtil;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {

	@Bean
	@LoadBalanced
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

	@Bean
	public HttpUtil restCommunicator() {
		return new HttpUtil(restTemplate());
	}

}
