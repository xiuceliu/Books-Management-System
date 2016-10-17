package com.hp.bookdb;

public class Book {
	private String ISBN;
	private String authorID;
	private String publisher;
	private String title;
	private String publishDate;
	private double price;
	
	public String getISBN() {
		return ISBN;
	}
	
	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}
	
	public String getAuthorID() {
		return authorID;
	}
	
	public void setAuthorID(String authorID) {
		this.authorID = authorID;
	}
	
	public String getPublisher() {
		return publisher;
	}
	
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getPublishDate() {
		return publishDate;
	}
	
	public void setPublishDate(String publishDate) {
		this.publishDate = publishDate;
	}
	
	public double getPrice() {
		return price;
	}
	
	public void setPrice(double price) {
		this.price = price;
	}
}
