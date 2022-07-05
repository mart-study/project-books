package com.project.books.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ApplicationConfiguration {

	@Bean
	RestTemplate restTemplate() {
		return new RestTemplate();
	}
}
