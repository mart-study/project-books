package com.project.books.service;

import java.util.List;
import java.util.Optional;

import com.project.books.dto.BookDto;
import com.project.books.dto.ItemDto;
import com.project.books.dto.KeySearchDto;
import com.project.books.model.KeySearch;

public interface BookService {

	public Optional<BookDto> saveBook(ItemDto itemDto);
	public Optional<BookDto> getBookById(String id);
	public void saveKeysearch(KeySearch keySearch);
	public List<KeySearchDto> getLatestKeySearch();
}
