package com.project.books.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class BookDto implements Serializable {
	
	private static final long serialVersionUID = -5008241177336901858L;
	private String title;
	private List<String> authors;
	private String publisher;
	private Date publishedDate;
	private String description;
	private List<IndustryIdentifierDto> industryIdentifiers;
	private int pageCount;
	private List<String> categories;
	private float averageRating;
	private String maturityRating;
	private String language;
	private String previewLink;
	private ImageLinksDto imageLinks;
	
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public List<IndustryIdentifierDto> getIndustryIdentifiers() {
		return industryIdentifiers;
	}
	public void setIndustryIdentifiers(List<IndustryIdentifierDto> industryIdentifiers) {
		this.industryIdentifiers = industryIdentifiers;
	}
	public int getPageCount() {
		return pageCount;
	}
	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}
	public List<String> getCategories() {
		return categories;
	}
	public void setCategories(List<String> categories) {
		this.categories = categories;
	}
	public float getAverageRating() {
		return averageRating;
	}
	public void setAverageRating(float averageRating) {
		this.averageRating = averageRating;
	}
	public String getMaturityRating() {
		return maturityRating;
	}
	public void setMaturityRating(String maturityRating) {
		this.maturityRating = maturityRating;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public String getPreviewLink() {
		return previewLink;
	}
	public void setPreviewLink(String previewLink) {
		this.previewLink = previewLink;
	}
	public ImageLinksDto getImageLinks() {
		return imageLinks;
	}
	public void setImageLinks(ImageLinksDto imageLinks) {
		this.imageLinks = imageLinks;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
