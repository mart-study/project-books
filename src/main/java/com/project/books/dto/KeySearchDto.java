package com.project.books.dto;

public class KeySearchDto {

	private String title;
	private String author;
	
	public KeySearchDto() {
		
	}
	
	public KeySearchDto(String title, String author) {
		this.title = title;
		this.author = author;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}

	@Override
	public String toString() {
		return "KeySearchDto [title=" + title + ", author=" + author + "]";
	}
}
