package com.project.books.model;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="key_search")
public class KeySearch {

	@Id
	private String id;
	private String title;
	private String author;
	private Date searchDate = new Date();
	
	public KeySearch() {
		
	}
	
	public KeySearch(String title, String author) {
		this.title = title;
		this.author = author;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
	public Date getSearchDate() {
		return searchDate;
	}
	public void setSearchDate(Date searchDate) {
		this.searchDate = searchDate;
	}

	@Override
	public String toString() {
		return "KeySearch [id=" + id + ", title=" + title + ", author=" + author + ", searchDate=" + searchDate + "]";
	}
	
}
