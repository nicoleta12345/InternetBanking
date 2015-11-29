<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<%@ page isELIgnored="false"%>
</head>
<body>
	<form:form action="getAccount"
		method="post">

		<label>Select an account</label>
		<select id="account" name="accountNumber">
			<c:forEach items="${accountFormDataDto.userAccounts}" var="account">
				<option value="${account.accountNumber}">${account.accountNumber}</option>
			</c:forEach>
		</select>

		<button type="submit">Details</button>

	</form:form>
</body>
</html>