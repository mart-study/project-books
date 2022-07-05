package com.project.books.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:google-books.properties")
public class GoogleBookProperties {

	@Value( "${base-url}" )
	private String baseUrl;
	
	@Value( "${api-key}" )
	private String apiKey;

	public String getBaseUrl() {
		return baseUrl;
	}

	public String getApiKey() {
		return apiKey;
	}
}
