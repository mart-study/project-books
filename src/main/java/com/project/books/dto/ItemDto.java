package com.project.books.dto;

import java.io.Serializable;

public class ItemDto implements Serializable {
	
	private static final long serialVersionUID = -890522263069154811L;
	private String id;
	private String etag;
	private String selfLink;
	private BookDto volumeInfo;
	
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
	public BookDto getVolumeInfo() {
		return volumeInfo;
	}
	public void setVolumeInfo(BookDto volumeInfo) {
		this.volumeInfo = volumeInfo;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
