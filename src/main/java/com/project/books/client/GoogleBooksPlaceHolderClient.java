package com.project.books.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.books.dto.SearchBookResultDto;

@FeignClient(value = "gbplaceholder", 
url = "https://www.googleapis.com/books/v1/volumes")
public interface GoogleBooksPlaceHolderClient {

	@RequestMapping(method = RequestMethod.GET, value = "?q={qWord}&maxResults={maxResults}&orderBy={orderBy}&key={key}")
	public ResponseEntity<SearchBookResultDto> searchBookByTitleAndAuthor(@RequestParam String qWord, 
			@RequestParam int maxResults, 
			@RequestParam String orderBy,
			@RequestParam String key);
}
