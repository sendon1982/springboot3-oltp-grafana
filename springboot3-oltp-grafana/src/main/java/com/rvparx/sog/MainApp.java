package com.rvparx.sog;

import io.opentelemetry.api.GlobalOpenTelemetry;
import io.opentelemetry.api.OpenTelemetry;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

@Slf4j
@SpringBootApplication
public class MainApp {

	public static void main(String[] args) {
		SpringApplication.run(MainApp.class, args);
	}

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}

	@RestController
	@RequiredArgsConstructor
	public static class DemoController {

		private final RestTemplate restTemplate;

		@GetMapping("/hello")
		public String hello() {
			log.info("Hitting hello endpoint and go to next");
			String response = restTemplate.getForObject("http://localhost:8080/world", String.class);
			return "Hello from Spring Boot! " + response;
		}

		@GetMapping("/world")
		public String world(){
			log.info("Hitting world endpoint and go to next");
			return "World";
		}
	}

}
