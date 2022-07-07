package com.project.books.resource;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.AutoConfigureWebClient;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.project.books.properties.GoogleBookProperties;

@AutoConfigureMockMvc
@WebMvcTest(controllers = BookResource.class)
@AutoConfigureWebClient(registerRestTemplate = true)
@Import(GoogleBookProperties.class)
public class BookResourceTest {

	@Autowired
	private MockMvc mockMvc;
	
	@Test
	public void searchBook_success() throws Exception {
		mockMvc.perform(get("/search")
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.param("title", "flowers")
				.param("author", "keyes"))
		.andExpect(content().contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andDo(print());
	}
	
	@Test
	public void getBook_success() throws Exception {
		mockMvc.perform(get("/_oG_iTxP1pIC")
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON_VALUE))
		.andExpect(content().contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andDo(print());
	}
	
	@Test
	public void downloadDescription_success() throws Exception {
		mockMvc.perform(get("/download/_oG_iTxP1pIC/description"))
		.andExpect(content().contentType(MediaType.TEXT_PLAIN))
		.andExpect(status().isOk())
		.andDo(print());
	}
	
	@Test
	public void downloadImage_success() throws Exception {
		mockMvc.perform(get("/download/_oG_iTxP1pIC/image"))
		.andExpect(content().contentType(MediaType.IMAGE_JPEG))
		.andExpect(status().isOk());
	}
}
