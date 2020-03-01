package webtech.RealEstateManagementSystemDataStore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class RealEstateManagementSystemDataStoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(RealEstateManagementSystemDataStoreApplication.class, args);
	}

}
