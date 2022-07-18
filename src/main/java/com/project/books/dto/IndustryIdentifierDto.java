package com.project.books.dto;

import java.io.Serializable;

public class IndustryIdentifierDto implements Serializable {

	private static final long serialVersionUID = -1354311876946859083L;
	private String type;
	private String identifier;
	
	public IndustryIdentifierDto() {
		
	}
	
	public IndustryIdentifierDto(String type, String identifier) {
		super();
		this.type = type;
		this.identifier = identifier;
	}
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getIdentifier() {
		return identifier;
	}
	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "IndustryIdentifierDto [type=" + type + ", identifier=" + identifier + "]";
	}
	
}
