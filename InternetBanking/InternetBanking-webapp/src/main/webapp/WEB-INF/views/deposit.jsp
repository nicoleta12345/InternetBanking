<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Deposit</title>
</head>
<body>
	<c:if test="${not empty errorMessage}">
		<c:out value="${errorMessage}" />
	</c:if>
	<form:form modelAttribute="depositTransfer" action="deposit"
		method="post">
		<label>Receiver Account</label>
		<input type="text" name="receiverAccountNumber" />
		<label>Value</label>
		<input type="text" name="value" />

		<button type="submit">Make Transaction</button>

	</form:form>

</body>
</html>