package fr.insa.ms.ConfigWebService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class ConfigWebServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConfigWebServiceApplication.class, args);
	}

}
