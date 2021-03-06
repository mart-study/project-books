package com.project.books.resource;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.imageio.ImageIO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.books.dto.BookDto;
import com.project.books.dto.ImageLinksDto;
import com.project.books.dto.ItemDto;
import com.project.books.dto.KeySearchDto;
import com.project.books.dto.SearchBookResponseDto;
import com.project.books.dto.SearchBookResultDto;
import com.project.books.model.KeySearch;
import com.project.books.properties.GoogleBookProperties;
import com.project.books.service.BookService;

@RestController
public class BookResource {
	
	private Logger logger = LoggerFactory.getLogger(BookResource.class);
	
	@Autowired
	private GoogleBookProperties properties;
	
	@Autowired
	private BookService bookService;
	
	@Autowired
	private com.project.books.client.GoogleBooksPlaceHolderClient googleBooksPlaceHolderClient;
	
	/**
	 * Search book by title and/or author name
	 * @param title
	 * @param author
	 * @return
	 */
	@GetMapping("/search")
	public ResponseEntity<List<SearchBookResponseDto>> searchBook(@RequestParam(value = "title", required=true) String title, 
			@RequestParam(value = "author", required=false) String author) {
		
		ResponseEntity<SearchBookResultDto> response = 
				googleBooksPlaceHolderClient.searchBookByTitleAndAuthor(title.concat("+inauthor:").concat(author),
						5, "newest", properties.getApiKey());
		
		List<SearchBookResponseDto> result = new ArrayList<>();
		response.getBody().getItems().stream().forEach(item -> {
			SearchBookResponseDto dto = new SearchBookResponseDto();
			dto.setId(item.getId());
			dto.setTitle(item.getVolumeInfo().getTitle());
			dto.setAuthors(item.getVolumeInfo().getAuthors());
			dto.setPublisher(item.getVolumeInfo().getPublisher());
			dto.setPublishedDate(item.getVolumeInfo().getPublishedDate());
			result.add(dto);
		});
		logger.info("Search books with title: " + title + " and author: " + author);
		
		// save key search
		KeySearch keySearch = new KeySearch(title, author);
		bookService.saveKeysearch(keySearch);
		logger.info("Save key search books with title: " + title + " and author: " + author);
		
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	/**
	 * Get book details using id from search result
	 * @param id
	 * @return
	 */
	@GetMapping("/{id}")
	public ResponseEntity<BookDto> getBook(@PathVariable String id) {
		Optional<BookDto> bookOpt = bookService.getBookById(id);
		if (bookOpt.isPresent()) {
			logger.info("Get book by id from database: " + id);
			return new ResponseEntity<>(bookOpt.get(), HttpStatus.OK);
		} else {
			ResponseEntity<ItemDto> response = googleBooksPlaceHolderClient.getBookById(id);
			bookOpt = bookService.saveBook(response.getBody());
			logger.info("Get book by id from google apis: " + id);
			return new ResponseEntity<>(bookOpt.get(), HttpStatus.OK);
		}
	}
	
	/**
	 * Download book's description
	 * @param id
	 * @return
	 * @throws IOException
	 */
	@GetMapping("/download/{id}/description")
	public ResponseEntity<byte[]> downloadBookDescription(@PathVariable String id) throws IOException {
		BookDto book = new BookDto();
		Optional<BookDto> bookOpt = bookService.getBookById(id);
		if (bookOpt.isPresent()) {
			logger.info("Get book's description from database: " + id);
			book = bookOpt.get();
		} else {
			ResponseEntity<ItemDto> response = googleBooksPlaceHolderClient.getBookById(id);
			
			logger.info("Get book's description from google apis: " + id);
			book = response.getBody().getVolumeInfo();
		}
		
		String description = book.getDescription();
		if (description != null) description.replaceAll("<br>", "\n");
		
		HttpHeaders headersResponse = new HttpHeaders();
		headersResponse.setContentType(MediaType.TEXT_PLAIN);
		headersResponse.add("content-disposition", "attachment; filename=" + 
				book.getTitle().replaceAll("\\s+", "_").concat(".txt"));
		return new ResponseEntity<>(description == null ? null : description.getBytes(), 
				headersResponse, HttpStatus.OK);
	}
	
	/**
	 * Download book's image
	 * @param id
	 * @return
	 * @throws IOException
	 */
	@GetMapping("/download/{id}/image")
	public ResponseEntity<byte[]> downloadBookImage(@PathVariable String id) throws IOException {
		BookDto book = new BookDto();
		Optional<BookDto> bookOpt = bookService.getBookById(id);
		if (bookOpt.isPresent()) {
			logger.info("Get book's thumbnail link from database: " + id);
			book = bookOpt.get();
		} else {
			ResponseEntity<ItemDto> response = googleBooksPlaceHolderClient.getBookById(id);
		
			logger.info("Get book's thumbnail from google apis: " + id);
			book = response.getBody().getVolumeInfo();
		}
		
		ImageLinksDto imageLinks = book.getImageLinks();
		
		HttpHeaders headersResponse = new HttpHeaders();
		headersResponse.setContentType(MediaType.IMAGE_JPEG);
		headersResponse.add("content-disposition", "attachment; filename=" + 
				book.getTitle().replaceAll("\\s+", "_").concat(".jpeg"));
		
		if (imageLinks != null && imageLinks.getThumbnail() != null) {
			URL imageUrl = new URL(imageLinks.getThumbnail().replace("http", "https"));
			BufferedImage image = ImageIO.read(imageUrl);
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ImageIO.write(image, "jpg", baos);
			baos.close();
			
			return new ResponseEntity<>(baos.toByteArray(), headersResponse, HttpStatus.OK);
		}
		
		return new ResponseEntity<>(null, headersResponse, HttpStatus.OK);
	}
	
	@GetMapping("/latest-search")
	public ResponseEntity<List<KeySearchDto>> getLatestKeySearch() {
		List<KeySearchDto> keySearchList = bookService.getLatestKeySearch();
		logger.info("Get latest key search");
		
		return new ResponseEntity<>(keySearchList, HttpStatus.OK); 
	}
}
