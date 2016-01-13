<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">

<!-- Optional theme -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap-theme.min.css">

<!-- Latest compiled and minified JavaScript -->
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>

<link href="<c:url value='/resources/app.css' />" rel="stylesheet"></link>
</head>
<body>

<spring:url value = "/logout" var = "loggOutUrl" />

<nav class="navbar navbar-default  ">
  <div class="container-fluid ">
    <div class="navbar-header ">
       <a class="navbar-brand" href="#">
        <img class="brandImg" alt="" src="${pageContext.request.contextPath}/images/bank2.jpg">
      </a> 
      <p class="navbar-text">    Internet Banking    </p>
      <button type="button" class="btn btn-default navbar-btn navbar-right" onClick="location.href='${loggOutUrl}'">Log out</button>
    </div>
  </div>
</nav>

	
</body>
</html>