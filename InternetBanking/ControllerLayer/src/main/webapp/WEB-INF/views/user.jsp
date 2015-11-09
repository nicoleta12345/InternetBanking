<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Spring4 MVC -HelloWorld</title>
<%@ page isELIgnored="false"%>
</head>
<body>
	<form:form modelAttribute="userAttribute" action="getUser" method="post">

		<label>User Id</label> <input type="text" name="id" />

		<button type="submit">GetUser</button>
	</form:form>
</body>
</html>