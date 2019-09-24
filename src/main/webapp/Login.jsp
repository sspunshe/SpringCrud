<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<%
		if (request.getAttribute("msg") != null) {
	%>
	<p> ${msg}</p>
	<%
		}
	%>
	<form action="login" method="post">
		<h1>Login</h1>
		Email ID: <input type="email" name="email" required="required">
		<br> Password: <input type="password" name="pass"
			required="required"> <br> <input type="submit"
			name="Login" value="Login">
		<button>
			<a href="index.jsp">Register</a>
		</button>
	</form>
</body>
</html>