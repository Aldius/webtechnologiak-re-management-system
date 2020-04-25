package hu.elte.webtechnologiak.realestatedocumenthandling;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class RealEstateDocumentHandlingApplication {

	public static void main(String[] args) {
		SpringApplication.run(RealEstateDocumentHandlingApplication.class, args);
	}

}
