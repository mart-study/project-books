package com.project.books.properties;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest(classes = GoogleBookProperties.class)
@TestPropertySource(locations = "classpath:google-books.properties")
public class GoogleBookPropertiesUnitTest {

	@Value( "${base-url}" )
	private String baseUrl;
	
	@Value( "${api-key}" )
	private String apiKey;
	
	@Test
	public void checkIfPropertyIsProperlyInjected() {
		assertThat(this.baseUrl).isEqualTo("https://www.googleapis.com/books/v1/volumes");
		assertThat(this.baseUrl).isNotEqualTo("");
		assertThat(this.apiKey).isEqualTo("put your google credential key");
		assertThat(this.apiKey).isNotEqualTo("");
	}
}
