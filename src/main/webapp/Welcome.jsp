<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<p>${msg}</p>
	<%
		if (session.getAttribute("email") != null) {
	%>
	<h1>
		Welcome&nbsp;<%
		out.println(session.getAttribute("email"));
	%>
	</h1>
	<a href="ShowUser.jsp">Show User</a>
	<a href="AddUser.jsp">Add User</a>
	<a href="logout">Logout</a>|
	<%
		} else {
	%>
	<h3>You are not in session...Login Again...</h3>
	<%
		}
	%>
</body>
</html>