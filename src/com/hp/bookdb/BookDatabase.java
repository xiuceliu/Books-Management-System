package com.hp.bookdb;

import java.util.ArrayList;
import java.sql.*;

public class BookDatabase {
	private static final String DRIVER = "com.mysql.jdbc.Driver";
	
	private static final String LOGIN_NAME = "root";
	private static final String LOGIN_PASSWORD = "toor";
	private static final String URL = "jdbc:mysql://localhost:3306/BookDB?characterEncoding=utf8";

	
//	private static final String LOGIN_NAME = "3n4mox2om2"; 
//	private static final String LOGIN_PASSWORD = "kwmx32zzllx553klyx10mhyw0z24kk5hwy14jxzm";
//	private static final String URL = "jdbc:mysql://w.rdc.sae.sina.com.cn:3307/app_galahadmoye?characterEncoding=utf8";

//	private static final String LOGIN_NAME = System.getenv("ACCESSKEY");
//	private static final String LOGIN_PASSWORD = System.getenv("SECRETKEY");
//	String URL = String.format("jdbc:mysql://%s:%s/%s", System.getenv("MYSQL_HOST"), System.getenv("MYSQL_PORT"), System.getenv("MYSQL_DB"));
	
	private Book book;
	private Author author;
	private ArrayList<Book> books = new ArrayList<>();
	private ArrayList<Author> authors = new ArrayList<>();
	private ArrayList<String> showAuthors = new ArrayList<>();
	
	public ArrayList<String> getShowAuthors() {
		return showAuthors;
	}

	public void setShowAuthors(ArrayList<String> showAuthors) {
		this.showAuthors = showAuthors;
	}

	public ArrayList<Book> getBooks() {
		return books;
	}

	public void setBooks(ArrayList<Book> books) {
		this.books = books;
	}

	public ArrayList<Author> getAuthors() {
		return authors;
	}

	public void setAuthors(ArrayList<Author> authors) {
		this.authors = authors;
	}

	public Book getBook() {
		return book;
	}
	
	public void setBook(Book book) {
		this.book = book;
	}
	
	public Author getAuthor() {
		return author;
	}
	
	public void setAuthor(Author author) {
		this.author = author;
	}
	
	public void searchAuthors (String authorName) {
		Connection connection;
		try {
			Class.forName(DRIVER);
			connection = DriverManager.getConnection(URL, LOGIN_NAME, LOGIN_PASSWORD);
			if (connection.isClosed()) {
				System.out.println("connect failed!");
				return;
			}
			Statement statement = connection.createStatement();
			String searchSql = "select * from Book where AuthorID in (select AuthorID from Author where Name='" + authorName + "');";
			ResultSet resultSet = statement.executeQuery(searchSql);
			while (resultSet.next()) {
				Book newBook = new Book();
				newBook.setAuthorID(resultSet.getString("AuthorID"));
				newBook.setISBN(resultSet.getString("ISBN"));
				newBook.setPrice(resultSet.getDouble("Price"));
				newBook.setPublishDate(resultSet.getString("PublishDate"));
				newBook.setPublisher(resultSet.getString("Publisher"));
				newBook.setTitle(resultSet.getString("Title"));
				this.books.add(newBook);
			}
			connection.close();
		}
		catch (ClassNotFoundException e) {
			System.out.println("Sorry, can't find the driver!");
			e.printStackTrace();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void bookDetails (String title, String ISBN) {
		for (int i = 0; i < this.books.size(); i++) {
			if (this.books.get(i).getTitle().equals(title) && this.books.get(i).getISBN().equals(ISBN) && i != 0) {
				Book temp = new Book();
				temp.setISBN(this.books.get(0).getISBN());
				temp.setAuthorID(this.books.get(0).getAuthorID());
				temp.setTitle(this.books.get(0).getTitle());
				temp.setPublisher(this.books.get(0).getPublisher());
				temp.setPublishDate(this.books.get(0).getPublishDate());
				temp.setPrice(this.books.get(0).getPrice());
				this.books.get(0).setISBN(this.books.get(i).getISBN());
				this.books.get(0).setAuthorID(this.books.get(i).getAuthorID());
				this.books.get(0).setTitle(this.books.get(i).getTitle());
				this.books.get(0).setPublisher(this.books.get(i).getPublisher());
				this.books.get(0).setPublishDate(this.books.get(i).getPublishDate());
				this.books.get(0).setPrice(this.books.get(i).getPrice());
				this.books.remove(i);
				this.books.add(temp);
				break;
			}
		}
		Connection connection;
		try {
			Class.forName(DRIVER);
			connection = DriverManager.getConnection(URL, LOGIN_NAME, LOGIN_PASSWORD);
			if (connection.isClosed()) {
				System.out.println("connect failed!");
				return;
			}
			Statement statement = connection.createStatement();
			String authorSql = "select * from Author where AuthorID='" + this.books.get(0).getAuthorID() + "';";
			ResultSet resultSet = statement.executeQuery(authorSql);
			while (resultSet.next()) {
				Author newAuthor = new Author();
				newAuthor.setAge(resultSet.getInt("Age"));
				newAuthor.setAuthorID(resultSet.getString("AuthorID"));
				newAuthor.setCountry(resultSet.getString("Country"));
				newAuthor.setName(resultSet.getString("Name"));
				this.authors.add(newAuthor);
			}
			connection.close();
		}
		catch (ClassNotFoundException e) {
			System.out.println("Sorry, can't find the driver!");
			e.printStackTrace();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void bookDelete (String title, String ISBN) {
		Connection connection;
		try {
			Class.forName(DRIVER);
			connection = DriverManager.getConnection(URL, LOGIN_NAME, LOGIN_PASSWORD);
			if (connection.isClosed()) {
				System.out.println("connect failed!");
				return;
			}
			Statement statement = connection.createStatement();
			String searchSql = "delete from Book where title='" + title + "' and ISBN='" + ISBN + "';";
			statement.executeUpdate(searchSql);
			connection.close();
			for (int i = 0; i < this.books.size(); i++) {
				if (this.books.get(i).getISBN().equals(ISBN)) {
					this.books.remove(i);
					break;
				}
			}
		}
		catch (ClassNotFoundException e) {
			System.out.println("Sorry, can't find the driver!");
			e.printStackTrace();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void showAll() {
		Connection connection;
		try {
			Class.forName(DRIVER);
			connection = DriverManager.getConnection(URL, LOGIN_NAME, LOGIN_PASSWORD);
			if (connection.isClosed()) {
				System.out.println("connect failed!");
				return;
			}
			Statement statement = connection.createStatement();
			String searchSql = "select Name from Author;";
			ResultSet resultSet = statement.executeQuery(searchSql);
			while (resultSet.next()) {
				this.showAuthors.add(resultSet.getString("Name"));
			}
			connection.close();
		}
		catch (ClassNotFoundException e) {
			System.out.println("Sorry, can't find the driver!");
			e.printStackTrace();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}