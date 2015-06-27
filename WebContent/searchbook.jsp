<jsp:useBean id="utente" scope="session" class="it.univr.is.entity.Utente"></jsp:useBean>
<jsp:include page="header.jsp" />
<%@page import="java.util.*"%>
<%@page import="it.univr.is.entity.Libro"%>

<head>
<title>Gestisci libri</title>
</head>
	
<% if(utente.getEmail() == null){ %>
<div class="container">
	<p class="bg-danger">Errore: Per vedere questa pagina bisogna essere loggati.</p>
</div>
<% }else{  %>

	<div class="container">

      <div class="jumbotron">
        <h2>Cerca libro</h2>
		
        <form action="MainServlet" method="POST">
		  <div class="form-group">
		    <label for="titolo">Titolo</label>
		    <input type="text" class="form-control" name="titolo">
		  </div>
		  
		  <div class="form-group">
		    <label for="autore">Autore</label>
		    <input type="text" class="form-control" name="autore">
		  </div>
		  
		  <div class="form-group">
		    <label for="categoria">Categoria</label>
		    <input type="text" class="form-control" name="categoria">
		  </div>
		  
		  <div class="form-group">
		    <label for="categoria2">Categoria2</label>
		    <input type="text" class="form-control" name="categoria2">
		  </div>
		  
		  <div class="form-group">
		    <label for="edizione">Edizione</label>
		    <input type="text" class="form-control" name="edizione">
		  </div>
		  
		  <div class="form-group">
		    <label for="isbn">ISBN</label>
		    <input type="text" class="form-control" name="isbn">
		  </div>
		  
		  <button type="submit" class="btn btn-default" name="mode" value="cerca_libro">Cerca</button>
		</form>

      </div> <!-- /jumbotron -->

    </div> <!-- /container -->
    
<% } %>
    
<jsp:include page="footer.jsp" />