package fr.insa.ms.DiscoveryWebService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class DiscoveryWebServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(DiscoveryWebServiceApplication.class, args);
	}

}
