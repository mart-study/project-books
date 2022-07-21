package com.project.books.resource;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import com.project.books.client.GoogleBooksPlaceHolderClient;
import com.project.books.dto.BookDto;
import com.project.books.dto.ItemDto;
import com.project.books.dto.KeySearchDto;
import com.project.books.dto.SearchBookResultDto;
import com.project.books.properties.GoogleBookProperties;
import com.project.books.service.impl.BookServiceImpl;

@WebMvcTest(controllers = BookResource.class)
public class BookResourceTest {

	@MockBean 
	private GoogleBookProperties properties;
	
	@MockBean
	private BookServiceImpl bookService;
	
	@MockBean
	private GoogleBooksPlaceHolderClient googleBooksPlaceHolderClient;
	
	@Autowired
	private MockMvc mockMvc;
	
	@BeforeEach
	@DisplayName("Setup mocks for test")
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
		
		List<ItemDto> itemList = new ArrayList<>();
		itemList.add(itemDto);
		
		SearchBookResultDto searchBookResultDto = new SearchBookResultDto();
		searchBookResultDto.setTotalItems("1");
		searchBookResultDto.setItems(itemList);
		
		ResponseEntity<SearchBookResultDto> resSearchBook = new ResponseEntity<>(searchBookResultDto, HttpStatus.OK);
		ResponseEntity<ItemDto> resGetBook = new ResponseEntity<>(itemDto, HttpStatus.OK);
		
		Mockito.when(properties.getApiKey()).thenReturn("apiKey");
		
		Mockito.when(googleBooksPlaceHolderClient.searchBookByTitleAndAuthor("Detective Conan".concat("+inauthor:").concat("Aoyama Gosho"), 5, "newest", properties.getApiKey())).thenReturn(resSearchBook);
		Mockito.when(googleBooksPlaceHolderClient.getBookById("78h278")).thenReturn(resGetBook);
		
		List<KeySearchDto> keySearchList = new ArrayList<>();
		keySearchList.add(new KeySearchDto("Detective Conan", "Aoyama Gosho"));
		
		Mockito.when(bookService.getLatestKeySearch()).thenReturn(keySearchList);
	}
	
	@Test
	@DisplayName("Test search book, expeceted success")
	public void searchBook_success() throws Exception {
		mockMvc.perform(get("/search")
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.param("title", "Detective Conan")
				.param("author", "Aoyama Gosho"))
		.andExpect(content().contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andDo(print());
	}
	
	@Test
	@DisplayName("Test get book, expeceted success")
	public void getBook_success() throws Exception {
		mockMvc.perform(get("/78h278")
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON_VALUE))
		.andExpect(content().contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andDo(print());
	}
	
	@Test
	@DisplayName("Test download book's description, expeceted success")
	public void downloadDescription_success() throws Exception {
		mockMvc.perform(get("/download/78h278/description"))
		.andExpect(content().contentType(MediaType.TEXT_PLAIN))
		.andExpect(status().isOk())
		.andDo(print());
	}
	
	@Test
	@DisplayName("Test download book's image, expeceted success")
	public void downloadImage_success() throws Exception {
		mockMvc.perform(get("/download/78h278/image"))
		.andExpect(content().contentType(MediaType.IMAGE_JPEG))
		.andExpect(status().isOk());
	}
	
	@Test
	@DisplayName("Test get latest key search, expeceted success")
	public void latestKeySearch_success() throws Exception {
		mockMvc.perform(get("/latest-search")
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON_VALUE))
		.andExpect(content().contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andDo(print());
	}
}
