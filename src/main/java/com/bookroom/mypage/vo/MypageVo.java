package com.bookroom.mypage.vo;

public class MypageVo {
	private String order_Id;
	private String isbn;
	private String quantity;
	private String order_Date;
	private String payment;
	private String user_Id;
	private String bookName;
	
	public MypageVo() {}
	
	public MypageVo(String order_Id, String isbn, String quantity, String order_Date, String payment, String user_Id,
			String bookName) {
		this.order_Id = order_Id;
		this.isbn = isbn;
		this.quantity = quantity;
		this.order_Date = order_Date;
		this.payment = payment;
		this.user_Id = user_Id;
		this.bookName = bookName;
	}
	
	public String getOrder_Id() {
		return order_Id;
	}
	public void setOrder_Id(String order_Id) {
		this.order_Id = order_Id;
	}
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public String getQuantity() {
		return quantity;
	}
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
	public String getOrder_Date() {
		return order_Date;
	}
	public void setOrder_Date(String order_Date) {
		this.order_Date = order_Date;
	}
	public String getPayment() {
		return payment;
	}
	public void setPayment(String payment) {
		this.payment = payment;
	}
	public String getUser_Id() {
		return user_Id;
	}
	public void setUser_Id(String user_Id) {
		this.user_Id = user_Id;
	}
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	
	@Override
	public String toString() {
		return "MypageVo [order_Id=" + order_Id + ", isbn=" + isbn + ", quantity=" + quantity + ", order_Date="
				+ order_Date + ", payment=" + payment + ", user_Id=" + user_Id + ", bookName=" + bookName + "]";
	}
}
