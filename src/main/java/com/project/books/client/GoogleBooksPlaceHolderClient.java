package com.project.books.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.books.dto.ItemDto;
import com.project.books.dto.SearchBookResultDto;

@PropertySource("classpath:google-books.properties")
@FeignClient(value = "gbplaceholder", url = "${base-url}")
public interface GoogleBooksPlaceHolderClient {

	/**
	 * Search book by title (mandatory) and author
	 * @param qWord
	 * @param maxResults
	 * @param orderBy
	 * @param key
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, value = "?q={qWord}&maxResults={maxResults}&orderBy={orderBy}&key={key}")
	public ResponseEntity<SearchBookResultDto> searchBookByTitleAndAuthor(@RequestParam String qWord, 
			@RequestParam int maxResults, 
			@RequestParam String orderBy,
			@RequestParam String key);
	
	/**
	 * Get book information by id
	 * @param id
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public ResponseEntity<ItemDto> getBookById(@PathVariable String id);
}
