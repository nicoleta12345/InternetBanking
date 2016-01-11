<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>AccessDenied page</title>
</head>
<body>
	<div id="header">
		<jsp:include page="loggedInHeader.jsp" />
	</div>
	Dear
	<strong>${user}</strong>, You are not authorized to access this content
	<a href="<c:url value="/logout" />">Logout</a>
</body>
</html>