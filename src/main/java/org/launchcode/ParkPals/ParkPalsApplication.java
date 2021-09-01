package org.launchcode.ParkPals;

import org.launchcode.ParkPals.models.Park;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.util.logging.Logger;

@SpringBootApplication
public class ParkPalsApplication {

	public static void main(String[] args) {
		SpringApplication.run(ParkPalsApplication.class, args);
	}
}
