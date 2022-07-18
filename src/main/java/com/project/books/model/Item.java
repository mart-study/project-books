package com.project.books.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection="item")
public class Item {

	@Id
	private String id;
	private String etag;
	private String selfLink;
	private Book volumeInfo;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getEtag() {
		return etag;
	}
	public void setEtag(String etag) {
		this.etag = etag;
	}
	public String getSelfLink() {
		return selfLink;
	}
	public void setSelfLink(String selfLink) {
		this.selfLink = selfLink;
	}
	public Book getVolumeInfo() {
		return volumeInfo;
	}
	public void setVolumeInfo(Book volumeInfo) {
		this.volumeInfo = volumeInfo;
	}
	
	@Override
	public String toString() {
		return "Item [id=" + id + ", etag=" + etag + ", selfLink=" + selfLink + ", volumeInfo=" + volumeInfo + "]";
	}
	
	
}
