package com.project.books.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.modelmapper.ModelMapper;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.project.books.dto.BookDto;
import com.project.books.dto.ItemDto;
import com.project.books.model.Book;
import com.project.books.model.Item;
import com.project.books.repository.BookRepository;
import com.project.books.service.impl.BookServiceImpl;

@ExtendWith(SpringExtension.class)
public class BookServiceTest {
	
	@Spy
	private ModelMapper modelMapper;
	
	@Mock
	private BookRepository bookRepository  = mock(BookRepository.class);;
	
	@InjectMocks
	private BookServiceImpl bookService;
	
	@Test
	public void saveBook_success() {
		List<String> authors = new ArrayList<String>();
		authors.add("Aoyama Gosho");
		
		BookDto bookDto = new BookDto();
		bookDto.setTitle("Detective Conan");
		bookDto.setAuthors(authors);
		
		ItemDto itemDto = new ItemDto();
		itemDto.setId("78h278");
		itemDto.setVolumeInfo(bookDto);
		
		Item item = modelMapper.map(itemDto, Item.class);
		
		// make stub because method by mock object always returns null
		when(bookRepository.save(any())).thenReturn(item);
		
		Optional<BookDto> book = bookService.saveBook(itemDto);
		item = bookRepository.save(item);
		assertEquals(book.get().getTitle(), item.getVolumeInfo().getTitle());
	}
	
	@Test
	public void getBookById_success() {
		List<String> authors = new ArrayList<String>();
		authors.add("Daniel Keyes");
		authors.add("Al Feldstein");
		
		Book book = new Book();
		book.setTitle("The EC Archives: Shock Illustrated");
		book.setAuthors(authors);
		
		Item item = new Item();
		item.setId("MPa_DwAAQBAJ");
		item.setVolumeInfo(book);
		
		Optional<Item> itemOpt = Optional.of(item);
		when(bookRepository.findById("MPa_DwAAQBAJ")).thenReturn(itemOpt);
		
		Optional<BookDto> bookDto = bookService.getBookById("MPa_DwAAQBAJ");
		assertEquals(bookDto.get().getTitle(), book.getTitle());
        verify(bookRepository, times(1)).findById("MPa_DwAAQBAJ");
	}
}
