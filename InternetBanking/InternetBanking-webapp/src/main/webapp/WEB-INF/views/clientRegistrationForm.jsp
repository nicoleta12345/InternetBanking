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
				<p> <form:errors path="firstName" class="control-label" /> </p>
				
				<label for=lastName>Last Name</label> 
				<input
					type="text" class="form-control" id="lastName"
					placeholder="Last Name" name="lastName"> 
				<p> <form:errors path="lastName" class="control-label" /> </p>
					
				<label for=cnp>Cnp</label>
				<input type="text" class="form-control" id="cnp" placeholder="CNP"
					name="cnp"> 
				<p> <form:errors path="cnp" class="control-label" /> </p>
					
				<label for=email>Email</label> 
				<input type="text"
					class="form-control" id="email" placeholder="Email" name="email">
				<p> <form:errors path="email" class="control-label" /> </p>

				<label for=age>Age</label>
				<input type="text"
					class="form-control" id="age"
					placeholder="Age" name="age"> 
				<p> <form:errors path="age" class="control-label" /> </p>
					
				<label for=streetName>Street Name</label> <input type="text"
					class="form-control" id="streetName" placeholder="Street Name"
					name="address.streetName">
				<p> <form:errors path="address.streetName" class="control-label" /> </p>
					
				<label for=streetNumber>Street Number</label> <input type="text"
					class="form-control" id="streetNumber" placeholder="Street Number"
					name="address.streetNumber">
				<p> <form:errors path="address.streetNumber" class="control-label" /> </p>

				<label for=town>Town</label>
				<input type="text"
					class="form-control" id="town"
					placeholder="Town" name="address.town"> 
				<p> <form:errors path="address.town" class="control-label" /> </p>
					
				<label for=username>Username</label>
				<input type="text"
					class="form-control" id="username"
					placeholder="Username" name="username"> 
				<p> <form:errors path="username" class="control-label" /> </p>
				
				<label for=password>Password</label>
				<input type="text"
					class="form-control" id="password"
					placeholder="Password" name="password"> 
				<p> <form:errors path="password" class="control-label" /> </p>
			</div>
			
			<button type="submit" class="btn btn-primary">Register Client</button>
		</form:form>
	</div>
</body>
</html>