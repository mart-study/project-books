package com.project.books.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class SearchBookResponseDto implements Serializable {

	private static final long serialVersionUID = -7579049408026464870L;
	private String id;
	private String title;
	private List<String> authors;
	private String publisher;
	private Date publishedDate;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public List<String> getAuthors() {
		return authors;
	}
	public void setAuthors(List<String> authors) {
		this.authors = authors;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	
	public Date getPublishedDate() {
		return publishedDate;
	}
	public void setPublishedDate(Date publishedDate) {
		this.publishedDate = publishedDate;
	}
	
	@Override
	public String toString() {
		return "SearchBookResponseDto [id=" + id + ", title=" + title + ", authors=" + authors + ", publisher="
				+ publisher + ", publishedDate=" + publishedDate + "]";
	}
}
