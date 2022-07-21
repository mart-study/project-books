package com.project.books.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.project.books.dto.BookDto;
import com.project.books.dto.ItemDto;
import com.project.books.dto.KeySearchDto;
import com.project.books.model.Item;
import com.project.books.model.KeySearch;
import com.project.books.repository.BookRepository;
import com.project.books.repository.KeySearchRepository;
import com.project.books.service.BookService;

@Service
public class BookServiceImpl implements BookService {

	@Autowired
	private BookRepository bookRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private KeySearchRepository keySearchRepository;
	
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

	@Override
	public void saveKeysearch(KeySearch keySearch) {
		keySearchRepository.save(keySearch);
	}

	@Override
	public List<KeySearchDto> getLatestKeySearch() {
		Sort sort = Sort.by(Sort.Direction.DESC, "searchDate");
		Pageable pageable = PageRequest.of(0, 5, sort);
		
	    Page<KeySearch> pageResult = keySearchRepository.findAll(pageable);
	    
	    List<KeySearchDto> keySearchList = new ArrayList<>();
	    pageResult.getContent().stream().forEach(value -> {
	    	KeySearchDto ks = modelMapper.map(value, KeySearchDto.class);
	    	keySearchList.add(ks);
	    });
		return keySearchList;
	}
}
