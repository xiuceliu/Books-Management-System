<%@page import="com.hp.bookdb.BookDatabase"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link rel="stylesheet" type="text/css" href="index.css">
		<title>Library Home</title>
	</head>
	<body>
		<h1>Welcome to My Library</h1>
		<form action="search_author_name" method="get">
			<input name="authorName" type="text" placeholder="please input the author name" required="required">
			<input type="submit" value="search">
		</form>
		<br>
		<p>你可能对这些感兴趣：</p>
		<% BookDatabase db = new BookDatabase();
		   db.showAll();
		%>
		<table>
			<th>作者</th>
			<% for (int i = 0; i < db.getShowAuthors().size(); i++) { %>
			<tr>
				<td><% out.print(db.getShowAuthors().get(i) + "<br>"); %></td>
			</tr>
		   	<% } %> 
		</table>
	</body>
</html>