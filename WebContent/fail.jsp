<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Fail</title>
</head>
<body>
	<%
		out.print("fail to find the author or there are no book belong to the author!");
	%>
	<form method="get" action="index.jsp">
		<input type="submit" value="Home" />
	</form>
</body>
</html>