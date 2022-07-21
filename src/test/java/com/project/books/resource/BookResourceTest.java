package com.project.books.resource;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.AutoConfigureWebClient;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.project.books.dto.BookDto;
import com.project.books.dto.ItemDto;
import com.project.books.properties.GoogleBookProperties;
import com.project.books.service.impl.BookServiceImpl;

@WebMvcTest(controllers = BookResource.class)
@AutoConfigureWebClient(registerRestTemplate = true)
@Import(GoogleBookProperties.class)
public class BookResourceTest {

	@MockBean
	private BookServiceImpl bookService;
	
	@Autowired
	private MockMvc mockMvc;
	
	@BeforeEach
	public void before() {
		List<String> authors = new ArrayList<String>();
		authors.add("Aoyama Gosho");
		
		BookDto bookDto = new BookDto();
		bookDto.setTitle("Detective Conan");
		bookDto.setAuthors(authors);
		
		ItemDto itemDto = new ItemDto();
		itemDto.setId("78h278");
		itemDto.setVolumeInfo(bookDto);
		
		Optional<BookDto> bookOpt = Optional.of(bookDto);
		Mockito.when(bookService.getBookById("78h278")).thenReturn(bookOpt);
		
		Mockito.when(bookService.saveBook(itemDto)).thenReturn(bookOpt);
	}
	
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
		
		
		mockMvc.perform(get("/78h278")
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON_VALUE))
		.andExpect(content().contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andDo(print());
	}
	
	@Test
	public void downloadDescription_success() throws Exception {
		mockMvc.perform(get("/download/78h278/description"))
		.andExpect(content().contentType(MediaType.TEXT_PLAIN))
		.andExpect(status().isOk())
		.andDo(print());
	}
	
	@Test
	public void downloadImage_success() throws Exception {
		mockMvc.perform(get("/download/78h278/image"))
		.andExpect(content().contentType(MediaType.IMAGE_JPEG))
		.andExpect(status().isOk());
	}
}
