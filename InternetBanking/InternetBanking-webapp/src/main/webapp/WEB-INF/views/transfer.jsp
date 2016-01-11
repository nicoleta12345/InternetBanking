<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Transfer</title>
</head>
<body>
	<div id="header">
		<jsp:include page="loggedInHeader.jsp" />
	</div>

	<c:if test="${not empty errorMessage}">
		<c:out value="${errorMessage}" />
	</c:if>

	<form:form modelAttribute="transferTransactionDto" action="transfer"
		method="post">

		<label>Sender Account</label>
		<select id="account" name="senderAccountNumber">
			<c:forEach items="${userAccountsDto.userAccounts}" var="account">
				<option value="${account.accountNumber}">${account.accountNumber}</option>
			</c:forEach>
		</select>

		<label>Receiver Account</label>
		<input type="text" name="receiverAccountNumber" />
		<label>Value</label>
		<input type="text" name="value" />

		<button type="submit">Make Transfer</button>

	</form:form>
</body>
</html>