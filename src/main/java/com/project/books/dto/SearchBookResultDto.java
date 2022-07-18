package com.project.books.dto;

import java.io.Serializable;
import java.util.List;


public class SearchBookResultDto implements Serializable {
	
	private static final long serialVersionUID = -8395144110086804317L;
	private String kind;
	private String totalItems;
	private List<ItemDto> items;
	
	public List<ItemDto> getItems() {
		return items;
	}
	public void setItems(List<ItemDto> items) {
		this.items = items;
	}
	public String getKind() {
		return kind;
	}
	public void setKind(String kind) {
		this.kind = kind;
	}
	public String getTotalItems() {
		return totalItems;
	}
	public void setTotalItems(String totalItems) {
		this.totalItems = totalItems;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	@Override
	public String toString() {
		return "SearchBookResultDto [kind=" + kind + ", totalItems=" + totalItems + ", items=" + items + "]";
	}
}
