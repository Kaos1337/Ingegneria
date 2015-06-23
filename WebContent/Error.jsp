<!-- Pagina temporanea-->

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Pagina temporanea di indirizzamento per errori</title>
</head>
<body>
	<h1>ERRORE</h1>
	Errore: <%request.getAttribute("error"); %>
	<br>
	Lista attributi request:
	<br>
	<%request.getAttributeNames().toString(); %>
</body>
</html>