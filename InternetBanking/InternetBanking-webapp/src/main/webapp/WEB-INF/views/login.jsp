<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login page</title>
<link href="<c:url value='/static/css/bootstrap.css' />"
	rel="stylesheet"></link>
<link href="<c:url value='/static/css/app.css' />" rel="stylesheet"></link>
<link rel="stylesheet" type="text/css"
	href="//cdnjs.cloudflare.com/ajax/libs/font-awesome/4.2.0/css/font-awesome.css" />
</head>

<body>
	<c:url var="loginUrl" value="/login" />
	<form action="${loginUrl}" method="post">
		<c:if test="${param.error != null}">
			<p>Invalid username and password.</p>
		</c:if>
		<c:if test="${param.logout != null}">
			<p>You have been logged out successfully.</p>
		</c:if>
		<input type="text" id="username" name="username"
			placeholder="Enter Username" required> <input type="password"
			class="form-control" id="password" name="password"
			placeholder="Enter Password" required> <input type="hidden"
			name="${_csrf.parameterName}" value="${_csrf.token}" /> <input
			type="submit" value="Log in">
	</form>


</body>
</html>