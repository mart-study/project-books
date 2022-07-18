package com.project.books.service;

import java.util.Optional;

import com.project.books.dto.BookDto;
import com.project.books.dto.ItemDto;

public interface BookService {

	public Optional<BookDto> saveBook(ItemDto itemDto);
	public Optional<BookDto> getBookById(String id);
}
