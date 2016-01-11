<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<title>Insert title here</title>
</head>
<body>
	<div id="header">
		<jsp:include page="loggedInHeader.jsp" />
	</div>

	<div class="container">

		<h1>Pending Transactions</h1>

		<table class="table table-striped">
			<thead>
				<tr>
					<th>#ID</th>
					<th>Sender</th>
					<th>Receiver</th>
					<th>Value</th>
					<th>Action</th>
				</tr>
			</thead>

			<c:forEach var="transaction" items="${pendingTransactions}">
				<tr>
					<td>${transaction.id}</td>
					<td>${transaction.senderAccountNumber}</td>
					<td>${transaction.externalAccountNumber}</td>
					<td>${transaction.value}</td>
					<td><spring:url
							value="/admin/acceptTransaction/${transaction.id}"
							var="approveUrl" /> <spring:url
							value="/admin/declineTransaction/${transaction.id}"
							var="declineUrl" />

						<button class="btn btn-info"
							onclick="location.href='${approveUrl}'">Approve</button>
						<button class="btn btn-danger"
							onclick="location.href='${declineUrl}'">Decline</button></td>
				</tr>
			</c:forEach>
		</table>

	</div>

</body>
</html>