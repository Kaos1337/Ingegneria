<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<jsp:include page="header.jsp" />
	
	<div class="container">

      <div class="jumbotron">
        <h2>Hmmm</h2>
        
        <form action="MainServlet" method="POST">
		  <div class="form-group">
		    <label for="nome">Nome</label>
		    <!-- <input type="email" class="form-control" id="exampleInputEmail1" placeholder="Email">  -->
		    <input type="text" class="form-control" id="inputNome" name="nome" value=<%=request.getParameter("nome")%>>
		  </div>
		  
		  <div class="form-group">
		    <label for="cognome">Cognome</label>
		    <input type="text" class="form-control" name="cognome">
		  </div>
		  
		  <div class="form-group">
		    <label for="cognome">Email</label>
		    <input type="email" class="form-control" name="email">
		  </div>
		  
		  <div class="form-group">
		    <label for="cognome">Password</label>
		    <input type="password" class="form-control" placeholder="Password" name="password" >
		  </div>
		  
		  <div class="form-group">
		    <label for="cognome">Via</label>
		    <input type="text" class="form-control" name="via">
		  </div>
		  
		  <div class="form-group">
		    <label for="cognome">Civico</label>
		    <input type="text" class="form-control" name="civico">
		  </div>
		  
		  <div class="form-group">
		    <label for="cognome">Cap</label>
		    <input type="text" class="form-control" name="cap">
		  </div>
		  
		  <div class="form-group">
		    <label for="cognome">Città</label>
		    <input type="text" class="form-control" name="citta">
		  </div>
		  
		  <div class="form-group">
		    <label for="cognome">Provincia</label>
		    <input type="text" class="form-control" name="provincia">
		  </div>
		  
		  <div class="checkbox">
		    <label>
		      <input type="checkbox"> Accetto i termini.
		    </label>
		  </div>
		  
		  <button type="submit" class="btn btn-default" name="mode" value="iscrizione">Iscrizione</button>
		</form>

      </div> <!-- /jumbotron -->

    </div> <!-- /container -->
    
    <jsp:include page="footer.jsp" />

</body>
</html>