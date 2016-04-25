package io.pivotal.gemfire;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.context.annotation.ImportResource;

import javax.annotation.PostConstruct;

@SpringBootApplication
@ImportResource(value = "cache-config.xml")
public class GemfireClient {
	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(GemfireClient.class);
		app.run(args);
	}
}
