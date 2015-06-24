<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<jsp:useBean id="utente" scope="session" class="it.univr.is.entity.Utente"></jsp:useBean>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	Registrato come: <%=request.getParameter("nome") %><br>
	<%=utente.getNome() %>
	<br>
	<%=utente.getCognome() %>
	<br>
	<%=utente.getEmail() %>
	<br>
	<%=utente.getPassword() %>
	<br>
	<%=utente.getVia() %>
	<br>
	<%=utente.getCap() %>
	<br>
	<%=utente.getCitta() %>
	<br>
	<%=utente.getProvincia() %>
	<br>
</body>
</html>