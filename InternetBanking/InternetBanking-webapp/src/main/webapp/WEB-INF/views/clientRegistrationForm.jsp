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

<title>Register new client</title>
</head>
<body>
	<div class="container">
		<c:if test="${not empty errorMessage}">
			<c:out value="${errorMessage}" />
		</c:if>

		<form:form class="form-horizontal" modelAttribute="userDto"
			action="registerClient" method="POST">
			<div class="form-group">
				<label for=firstName>First Name</label> 
				<input type="text"
					class="form-control" id="firstName" placeholder="First Name"
					name="firstName"> 
					
				<label for=lastName>Last Name</label> 
				<input
					type="text" class="form-control" id="lastName"
					placeholder="Last Name" name="lastName"> 
					
				<label for=cnp>Cnp</label>
				<input type="text" class="form-control" id="cnp" placeholder="CNP"
					name="cnp"> 
					
				<label for=email>Email</label> 
				<input type="text"
					class="form-control" id="email" placeholder="Email" name="email">

				<label for=age>Age</label>
				<input type="text"
					class="form-control" id="age"
					placeholder="Age" name="age"> 
					
				<label for=streetName>Street Name</label> <input type="text"
					class="form-control" id="streetName" placeholder="Street Name"
					name="address.streetName">
					
				<label for=streetNumber>Street Number</label> <input type="text"
					class="form-control" id="streetNumber" placeholder="Street Number"
					name="address.streetNumber">

				<label for=town>Town</label>
				<input type="text"
					class="form-control" id="town"
					placeholder="Town" name="address.town"> 
					
				<label for=username>Username</label>
				<input type="text"
					class="form-control" id="username"
					placeholder="Username" name="username"> 
				
				<label for=password>Password</label>
				<input type="text"
					class="form-control" id="password"
					placeholder="Password" name="password"> 
			</div>
			
			<button type="submit" class="btn btn-primary">Register Client</button>
		</form:form>
	</div>
</body>
</html>