package com.project.books.service.impl;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.books.dto.BookDto;
import com.project.books.dto.ItemDto;
import com.project.books.model.Item;
import com.project.books.repository.BookRepository;
import com.project.books.service.BookService;

@Service
public class BookServiceImpl implements BookService {

	@Autowired
	private BookRepository bookRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	public Optional<BookDto> saveBook(ItemDto itemDto) {
		Item item = modelMapper.map(itemDto, Item.class);
		item = bookRepository.save(item);
		
		BookDto book = modelMapper.map(item.getVolumeInfo(), BookDto.class);
		return Optional.of(book);
	}

	@Override
	public Optional<BookDto> getBookById(String id) {
		Optional<Item> item = bookRepository.findById(id);
		Optional<BookDto> bookOpt = Optional.empty();
		if(item.isPresent()) {
			BookDto book = modelMapper.map(item.get().getVolumeInfo(), BookDto.class);
			bookOpt = Optional.of(book);
		}
		return bookOpt;
	}
}
