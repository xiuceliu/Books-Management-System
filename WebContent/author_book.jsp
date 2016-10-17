<%@ page import="java.util.ArrayList" %>
<%@ page language="java" import="com.hp.bookdb.BookDatabase" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link rel="stylesheet" type="text/css" href="author_book.css">
		<title>Author_Book</title>
	</head>
	<body>
	<table frame="hsides">
		<tr>
			<th>Title</th>
			<th>Operation</th>
		</tr>
		<%
			BookDatabase books = (BookDatabase)session.getAttribute("books");
			for (int i = 0; i < books.getBooks().size(); i++) {
		%>
		<tr>
			<td>
				<% out.print("<a href=\"" + request.getContextPath() + "/book_detail.action?title=" + books.getBooks().get(i).getTitle() + "&ISBN=" + books.getBooks().get(i).getISBN() + "\">" + books.getBooks().get(i).getTitle() + "</a>"); %>
			</td>
			<td>
				<% out.print("<a href=\"" + request.getContextPath() + "/delete_book.action?title=" + books.getBooks().get(i).getTitle() + "&ISBN=" + books.getBooks().get(i).getISBN() + "&deleteOpt=true" + "\">" + "删除" + "</a>"); %>
			</td>
		</tr>
		<%  
			}
			session.setAttribute("bddel", books);
		%>
	</table><br>
	<form action="index.jsp">
		<input type="submit" value="Home" />
	</form>
	</body>
</html>