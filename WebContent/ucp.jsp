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
		    <label for="via">Via</label> <span class="glyphicon glyphicon-question-sign" aria-hidden="true" data-toggle="tooltip" data-placement="top" title="Specificare la via dove sei reperibile"></span>
		    <input type="text" class="form-control" name="via" value="<%=utente.getVia()%>">
		  </div>
		  
		  <div class="form-group">
		    <label for="civico">Civico</label> <span class="glyphicon glyphicon-question-sign" aria-hidden="true" data-toggle="tooltip" data-placement="top" title="Specificare il numero civico della tua abitazione"></span>
		    <input type="text" class="form-control" name="civico" value="<%=utente.getCivico()%>">
		  </div>
		  
		  <div class="form-group">
		    <label for="cap">Cap</label> <span class="glyphicon glyphicon-question-sign" aria-hidden="true" data-toggle="tooltip" data-placement="top" title="Specificare il codice di avviamento postale"></span>
		    <input type="text" class="form-control" name="cap" value="<%=utente.getCap()%>">
		  </div>
		  
		  <div class="form-group">
		    <label for="citta">Citt�</label> <span class="glyphicon glyphicon-question-sign" aria-hidden="true" data-toggle="tooltip" data-placement="top" title="Specificare la vitt� in cui abiti"></span>
		    <input type="text" class="form-control" name="citta" value="<%=utente.getCitta()%>">
		  </div>
		  
		  <div class="form-group">
		    <label for="provincia">Provincia</label> <span class="glyphicon glyphicon-question-sign" aria-hidden="true" data-toggle="tooltip" data-placement="top" title="Specificare la provincia in cui abiti"></span>
		    <input type="text" class="form-control" name="provincia" value="<%=utente.getProvincia()%>">
		  </div>
		  
		  <div class="form-group">
		    <label for="password">Nuova Password</label> <span class="glyphicon glyphicon-question-sign" aria-hidden="true" data-toggle="tooltip" data-placement="top" title="Specificare una nuova password se desideri modificarla"></span>
		    <input type="password" class="form-control" placeholder="Password" name="password" >
		  </div>
		  
		  <div class="form-group">
		    <label for="password_attuale">Password Attuale</label> <span class="glyphicon glyphicon-question-sign" aria-hidden="true" data-toggle="tooltip" data-placement="top" title="Inserire la password per eseguire le modifiche"></span>
		    <input type="password" class="form-control" placeholder="Password" name="password_attuale" >
		  </div>
		  
		  <button type="submit" class="btn btn-default" name="mode" value="modifica_utente">Modifica</button>
		</form>
		
		<% if(request.getAttribute("info") != null){ %>
		<br><p class="bg-success"><%=request.getAttribute("info")%></p>
		<% } %>
		
		<% if(request.getAttribute("error") != null){ %>
		<br><p class="bg-danger"><%=request.getAttribute("error")%></p>
		<% } %>

      </div> <!-- /jumbotron -->

    </div> <!-- /container -->
    
<% } %>
    
<jsp:include page="footer.jsp" />