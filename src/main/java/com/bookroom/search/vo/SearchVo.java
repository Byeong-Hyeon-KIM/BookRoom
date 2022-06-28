package com.bookroom.search.vo;

import java.util.List;

import com.bookroom.book.vo.BookVo;

public class SearchVo {
	String lastBuildDate;
	String total;
	String start;
	String display;
	List<BookVo> items;
	
	public SearchVo() {};
	
	public SearchVo(String lastBuildDate, String total, String start, String display, List<BookVo> items) {
		super();
		this.lastBuildDate = lastBuildDate;
		this.total = total;
		this.start = start;
		this.display = display;
		this.items = items;
	}

	public String getLastBuildDate() {
		return lastBuildDate;
	}

	public void setLastBuildDate(String lastBuildDate) {
		this.lastBuildDate = lastBuildDate;
	}

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	public String getStart() {
		return start;
	}

	public void setStart(String start) {
		this.start = start;
	}

	public String getDisplay() {
		return display;
	}

	public void setDisplay(String display) {
		this.display = display;
	}

	public List<BookVo> getItems() {
		return items;
	}

	public void setItems(List<BookVo> items) {
		this.items = items;
	}
	
}
