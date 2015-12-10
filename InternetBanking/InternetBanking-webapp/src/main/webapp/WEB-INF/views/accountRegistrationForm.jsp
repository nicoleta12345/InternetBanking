<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">

<!-- Optional theme -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap-theme.min.css">

<!-- Latest compiled and minified JavaScript -->
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>

<title>Create account</title>
</head>
<body>
	<div class="container">
		<c:if test="${not empty errorMessage}">
			<c:out value="${errorMessage}" />
		</c:if>


		<form:form class="form-horizontal" modelAttribute="accountDetailsDto"
			action="registerAccount" method="POST">
			<div class="form-group">
				
				<input type="text" class="form-control" id="ownerCnp" placeholder="Owner CNP"
					name="ownerCnp"> 
				<p> <form:errors path="ownerCnp" class="control-label" /> </p>

				<div>
				<label>Account Type</label>
				<select id="accountType" name="accountType" required>
					<option value="Credit Account">Credit Account</option>
					<option value="Savings Account">Savings Account</option>
				</select>
				</div>
				
				<input type="text" class="form-control" id="accountNumber"
					placeholder="Account Number" name="accountNumber">
				<p> <form:errors path="accountNumber" class="control-label" /> </p>

				<!-- <label
					class="sr-only" for="amount">Amount (in
					dollars)</label> -->
				<div class="input-group">
					<div class="input-group-addon">$</div>
					<input type="number" class="form-control" id="amount"
						placeholder="Amount" name="amount">
					<div class="input-group-addon">.00</div>
					<p> <form:errors path="amount" class="control-label" /> </p>
				</div>
			</div>
			<button type="submit" class="btn btn-primary">Register
				account</button>
		</form:form>
	</div>
</body>
</html>