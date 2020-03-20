package hu.elte.webtechnologiak.realestaterecalc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;


@SpringBootApplication
@EnableEurekaClient
public class RealEstateRecalcApplication {

	public static void main(String[] args) {
		SpringApplication.run(RealEstateRecalcApplication.class, args);
	}
}
