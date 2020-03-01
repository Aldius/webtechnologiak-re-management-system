package webtech.RealEstateManagementSystemEurekaServer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class RealEstateManagementSystemEurekaServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(RealEstateManagementSystemEurekaServerApplication.class, args);
	}

}
