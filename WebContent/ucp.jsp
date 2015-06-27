<jsp:useBean id="utente" scope="session" class="it.univr.is.entity.Utente"></jsp:useBean>
<jsp:include page="header.jsp" />

<head>
<title>Pannello Utente</title>
</head>

<% if(session.getAttribute("id") == null){ %>
<div class="container">
	<p class="bg-danger">Errore: Per vedere questa pagina bisogna essere loggati.</p>
</div>
<% }else{  %>

	<div class="container">

      <!-- Main component for a primary marketing message or call to action -->
      <div class="jumbotron">
        <h2>Pannello Utente</h2>
        
		<form action="MainServlet" method="POST">
		  <div class="form-group">
		    <label for="via">Via</label>
		    <input type="text" class="form-control" name="via" value=<%=utente.getVia()%>>
		  </div>
		  
		  <div class="form-group">
		    <label for="civico">Civico</label>
		    <input type="text" class="form-control" name="civico" value=<%=utente.getCivico()%>>
		  </div>
		  
		  <div class="form-group">
		    <label for="cap">Cap</label>
		    <input type="text" class="form-control" name="cap" value=<%=utente.getCap()%>>
		  </div>
		  
		  <div class="form-group">
		    <label for="citta">Città</label>
		    <input type="text" class="form-control" name="citta" value=<%=utente.getCitta()%>>
		  </div>
		  
		  <div class="form-group">
		    <label for="provincia">Provincia</label>
		    <input type="text" class="form-control" name="provincia" value=<%=utente.getProvincia()%>>
		  </div>
		  
		  <div class="form-group">
		    <label for="password">Nuova Password</label>
		    <input type="password" class="form-control" placeholder="Password" name="password" >
		  </div>
		  
		  <div class="form-group">
		    <label for="password_attuale">Password Attuale</label>
		    <input type="password" class="form-control" placeholder="Password" name="password_attuale" >
		  </div>
		  
		  <button type="submit" class="btn btn-default" name="mode" value="modifica_utente">Modifica</button>
		</form>

      </div> <!-- /jumbotron -->

    </div> <!-- /container -->
    
<% } %>
    
<jsp:include page="footer.jsp" />