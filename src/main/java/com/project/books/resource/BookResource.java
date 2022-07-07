package com.project.books.resource;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.project.books.dto.BookDto;
import com.project.books.dto.ImageLinksDto;
import com.project.books.dto.ItemDto;
import com.project.books.dto.SearchBookResponseDto;
import com.project.books.dto.SearchBookResultDto;
import com.project.books.properties.GoogleBookProperties;

@RestController
public class BookResource {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private GoogleBookProperties properties;
	
	/**
	 * Search book by title and/or author name
	 * @param title
	 * @param author
	 * @return
	 */
	@GetMapping("/search")
	public ResponseEntity<List<SearchBookResponseDto>> searchBook(@RequestParam(value = "title", required=true) String title, 
			@RequestParam(value = "author", required=false) String author) {
		HttpHeaders headers = new HttpHeaders();
		headers.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
		
		HttpEntity<String> entity = new HttpEntity<>(headers);
		
		String url = UriComponentsBuilder.fromHttpUrl(properties.getBaseUrl())
				.queryParam("q", "{qWord}")
				.queryParam("maxResults", "{maxResults}")
				.queryParam("orderBy", "{orderBy}")
				.queryParam("key", "{key}")
				.encode().toUriString();
		
		Map<String, Object> params = new HashMap<>();
		params.put("qWord", title.concat("+inauthor:").concat(author));
		params.put("maxResults", 5);
		params.put("orderBy", "newest");
		params.put("key", properties.getApiKey());
		
		ResponseEntity<SearchBookResultDto> response = restTemplate.exchange(url, HttpMethod.GET, 
				entity, SearchBookResultDto.class, params);
		
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
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	/**
	 * Get book details using id from search result
	 * @param id
	 * @return
	 */
	@GetMapping("/{id}")
	public ResponseEntity<BookDto> getBook(@PathVariable String id) {
		String url = properties.getBaseUrl().concat("/").concat(id);
		HttpHeaders headers = new HttpHeaders();
		headers.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
		
		HttpEntity<String> entity = new HttpEntity<>(headers);
		
		ResponseEntity<ItemDto> response = restTemplate.exchange(url, HttpMethod.GET, 
				entity, ItemDto.class);
		
		return new ResponseEntity<>(response.getBody().getVolumeInfo(), HttpStatus.OK);
	}
	
	/**
	 * Download book's description
	 * @param id
	 * @return
	 * @throws IOException
	 */
	@GetMapping("/download/{id}/description")
	public ResponseEntity<byte[]> downloadBookDescription(@PathVariable String id) throws IOException {
		String url = properties.getBaseUrl().concat("/").concat(id);
		HttpHeaders headers = new HttpHeaders();
		headers.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
		
		HttpEntity<String> entity = new HttpEntity<>(headers);
		
		ResponseEntity<ItemDto> response = restTemplate.exchange(url, HttpMethod.GET, 
				entity, ItemDto.class);
		
		BookDto book = response.getBody().getVolumeInfo();
		String description = book.getDescription().replaceAll("<br>", "\n");
		
		HttpHeaders headersResponse = new HttpHeaders();
		headersResponse.setContentType(MediaType.TEXT_PLAIN);
		headersResponse.add("content-disposition", "attachment; filename=" + 
				book.getTitle().replaceAll("\\s+", "_").concat(".txt"));
		return new ResponseEntity<>(description.getBytes(), headersResponse, HttpStatus.OK);
	}
	
	/**
	 * Download book's image
	 * @param id
	 * @return
	 * @throws IOException
	 */
	@GetMapping("/download/{id}/image")
	public ResponseEntity<byte[]> downloadBookImage(@PathVariable String id) throws IOException {
		String url = properties.getBaseUrl().concat("/").concat(id);
		HttpHeaders headers = new HttpHeaders();
		headers.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
		
		HttpEntity<String> entity = new HttpEntity<>(headers);
		
		ResponseEntity<ItemDto> response = restTemplate.exchange(url, HttpMethod.GET, 
				entity, ItemDto.class);
		
		BookDto book = response.getBody().getVolumeInfo();
		ImageLinksDto imageLinks = book.getImageLinks();
		
		HttpHeaders headersResponse = new HttpHeaders();
		headersResponse.setContentType(MediaType.IMAGE_JPEG);
		headersResponse.add("content-disposition", "attachment; filename=" + 
				book.getTitle().replaceAll("\\s+", "_").concat(".jpeg"));
		
		URL imageUrl = new URL(imageLinks.getThumbnail().replace("http", "https"));
		BufferedImage image = ImageIO.read(imageUrl);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ImageIO.write(image, "jpg", baos);
		baos.close();
		
		return new ResponseEntity<>(baos.toByteArray(), headersResponse, HttpStatus.OK);
	}
}
