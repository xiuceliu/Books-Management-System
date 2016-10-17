<%@page import="com.hp.bookdb.BookDatabase"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link rel="stylesheet" type="text/css" href="author_book.css">
		<title>Book_Author</title>
	</head>
	<body>
		<% BookDatabase bd = (BookDatabase)session.getAttribute("book_author");	%>
		<% out.print(bd.getBooks().get(0).getTitle()); %> 's detail
		<table>
			<tr>
				<th>ISBN</th>
				<th>Publisher</th>
				<th>PublishDate</th>
				<th>Price</th>
			</tr>
			<tr>
				<td><% out.print(bd.getBooks().get(0).getISBN()); %></td>
				<td><% out.print(bd.getBooks().get(0).getPublisher()); %></td>
				<td><% out.print(bd.getBooks().get(0).getPublishDate()); %></td>
				<td><% out.print(bd.getBooks().get(0).getPrice()); %></td>
			</tr>
		</table><br>
		<% out.print(bd.getBooks().get(0).getTitle()); %> 's Author Info
		<table>
			<tr>
				<th>AuthorID</th>
				<th>Name</th>
				<th>Age</th>
				<th>Country</th>
			</tr>
			<tr>
				<td><% out.print(bd.getAuthors().get(0).getAuthorID()); %></td>
				<td><% out.print(bd.getAuthors().get(0).getName()); %></td>
				<td><% out.print(bd.getAuthors().get(0).getAge()); %></td>
				<td><% out.print(bd.getAuthors().get(0).getCountry()); %></td>
			</tr>
		</table><br>
		<% session.setAttribute("books", bd); %>
		<form action="author_book.jsp">
			<input type="submit" value="Return" />
		</form>
	</body>
</html>