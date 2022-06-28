package com.bookroom.cart.vo;

public class CartVo {
	private String order_Id;
	private String isbn;
	private String quantity;
	private String order_Date;
	private String payment;
	private String user_Id;
	private String bookName;
	private String img;
	private int price;
	private String auth;
	private String publisher;
	
	public CartVo() {}
	
	public CartVo(String order_Id, String isbn, String quantity, String order_Date, String payment, String user_Id,
			String bookName, String img, int price, String auth, String publisher) {
		this.order_Id = order_Id;
		this.isbn = isbn;
		this.quantity = quantity;
		this.order_Date = order_Date;
		this.payment = payment;
		this.user_Id = user_Id;
		this.bookName = bookName;
		this.img = img;
		this.price = price;
		this.auth = auth;
		this.publisher = publisher;
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
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getAuth() {
		return auth;
	}
	public void setAuth(String auth) {
		this.auth = auth;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	
	@Override
	public String toString() {
		return "MypageVo [order_Id=" + order_Id + ", isbn=" + isbn + ", quantity=" + quantity + ", order_Date="
				+ order_Date + ", payment=" + payment + ", user_Id=" + user_Id + ", bookName=" + bookName + "]";
	}
}
