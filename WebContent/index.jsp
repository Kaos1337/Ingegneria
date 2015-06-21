<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<form action="MainServlet" method="POST" >

	<table>
		<tr><td>	
		Nome: <input type="text" name="nome" ></td></tr>
		<tr><td>	
		Cognome: <input type="text" name="cognome"> </td></tr>
		<tr><td>	
		Email: <input type="text" name="email"></td></tr>
		<tr><td>	
		Password: <input type="text" name="password"></td></tr>
		<tr><td>	
		Via: <input type="text" name="via"></td></tr>
		<tr><td>	
		Civico: <input type="text" name="civico"></td></tr>
		<tr><td>	
		Cap: <input type="text" name="cap"></td></tr>
		<tr><td>	
		Città: <input type="text" name="citta"></td></tr>
		<tr><td>	
		Provincia: <input type="text" name="provincia"></td></tr>
		<tr><td>	
		Nazione: <input type="text" name="nazione"></td></tr>
	</table>
		
	
	<input type="submit" name="Mode" value="Iscrizione">

	
</form>

</body>
</html>