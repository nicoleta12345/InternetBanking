<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Deposit</title>
</head>
<body>
	<form:form action="deposit" method="post">
		<label>Receiver Account</label>
		<input type="text" name="receiverNumberAccount" />
		<label>Value</label>
		<input type="text" name="valueSent" />

		<button type="submit">Make Transaction</button>

	</form:form>

</body>
</html>