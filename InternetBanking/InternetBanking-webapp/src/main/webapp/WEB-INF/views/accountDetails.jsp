<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Account Details</title>
</head>
<body>
<body>
	<div id="header">
		<jsp:include page="loggedInHeader.jsp" />
	</div>
	
	<h2>Account Details</h2>
	<ul>
		<li>Account Number: ${accountDetailsDto.accountNumber}</li>
		<li>Account Value: ${accountDetailsDto.amount}</li>
	</ul>

</body>
</body>
</html>