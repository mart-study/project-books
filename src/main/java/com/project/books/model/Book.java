package com.project.books.model;

import java.util.Date;
import java.util.List;

public class Book {

	private String title;
	private List<String> authors;
	private String publisher;
	private Date publishedDate;
	private String description;
	private List<IndustryIdentifier> industryIdentifiers;
	private int pageCount;
	private List<String> categories;
	private float averageRating;
	private String maturityRating;
	private String language;
	private String previewLink;
	private ImageLinks imageLinks;
	
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
	public List<IndustryIdentifier> getIndustryIdentifiers() {
		return industryIdentifiers;
	}
	public void setIndustryIdentifiers(List<IndustryIdentifier> industryIdentifiers) {
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
	public ImageLinks getImageLinks() {
		return imageLinks;
	}
	public void setImageLinks(ImageLinks imageLinks) {
		this.imageLinks = imageLinks;
	}
	
	@Override
	public String toString() {
		return "Book [title=" + title + ", authors=" + authors + ", publisher=" + publisher + ", publishedDate="
				+ publishedDate + ", description=" + description + ", industryIdentifiers=" + industryIdentifiers
				+ ", pageCount=" + pageCount + ", categories=" + categories + ", averageRating=" + averageRating
				+ ", maturityRating=" + maturityRating + ", language=" + language + ", previewLink=" + previewLink
				+ ", imageLinks=" + imageLinks + "]";
	}
}
