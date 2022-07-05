package com.project.books.resource;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.project.books.dto.ItemDto;
import com.project.books.dto.SearchBookResultDto;
import com.project.books.properties.GoogleBookProperties;

@RestController
public class BookResource {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private GoogleBookProperties properties;
	
	@GetMapping("/search-book")
	public SearchBookResultDto searchBook(@RequestParam(value = "title", required=true) String title, 
			@RequestParam(value = "author", required=false) String author) {
		HttpHeaders headers = new HttpHeaders();
		headers.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
		
		HttpEntity<String> entity = new HttpEntity<>(headers);
		
		String url = UriComponentsBuilder.fromHttpUrl(properties.getBaseUrl())
				.queryParam("q", "{qWord}")
				.queryParam("maxResults", "{maxResults}")
				.queryParam("key", "{key}")
				.encode().toUriString();
		
		Map<String, Object> params = new HashMap<>();
		params.put("qWord", title.concat("+inauthor:").concat(author));
		params.put("maxResults", 5);
		params.put("key", properties.getApiKey());
		
		ResponseEntity<SearchBookResultDto> response = restTemplate.exchange(url, HttpMethod.GET, 
				entity, SearchBookResultDto.class, params);
		return response.getBody();
	}
	
	/**
	 * Get book details using id from search result
	 * @param id
	 * @return
	 */
	@GetMapping("/get-book/{id}")
	public ItemDto getBook(@PathVariable String id) {
		String url = properties.getBaseUrl().concat("/").concat(id);
		HttpHeaders headers = new HttpHeaders();
		headers.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
		
		HttpEntity<String> entity = new HttpEntity<>(headers);
		
		ResponseEntity<ItemDto> response = restTemplate.exchange(url, HttpMethod.GET, 
				entity, ItemDto.class);
		return response.getBody();
	}
	
}
