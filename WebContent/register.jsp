<jsp:useBean id="utente" scope="session" class="it.univr.is.entity.Utente"></jsp:useBean>
<jsp:include page="header.jsp" />

<% if(session.getAttribute("id") != null){
	response.setStatus(response.SC_MOVED_TEMPORARILY);
	response.setHeader("Location", "index.jsp");
}%>

<head>
<title>Registrazione</title>
</head>
	
	<div class="container">

      <div class="jumbotron">
        <h2>Registrazione</h2>
        
        <form action="MainServlet" method="POST">
		  <div class="form-group">
		    <label for="nome">Nome</label><span class="glyphicon glyphicon-question-sign" aria-hidden="true" data-toggle="tooltip" data-placement="right" title="Specificare il proprio nome. Obbligatorio"></span>
		    <!-- <input type="email" class="form-control" id="exampleInputEmail1" placeholder="Email">  -->
		    <input type="text" class="form-control" id="inputNome" name="nome" value=<%=request.getParameter("nome")%>>
		  </div>
		  
		  <div class="form-group">
		    <label for="cognome">Cognome</label><span class="glyphicon glyphicon-question-sign" aria-hidden="true" data-toggle="tooltip" data-placement="right" title="Specificare il proprio cognome. Obbligatorio"></span>
		    <input type="text" class="form-control" name="cognome">
		  </div>
		  
		  <div class="form-group">
		    <label for="email">Email</label><span class="glyphicon glyphicon-question-sign" aria-hidden="true" data-toggle="tooltip" data-placement="right" title="Specificare la propria email. Obbligatorio"></span>
		    <input type="email" class="form-control" name="email">
		  </div>
		  
		  <div class="form-group">
		    <label for="password">Password</label><span class="glyphicon glyphicon-question-sign" aria-hidden="true" data-toggle="tooltip" data-placement="right" title="Specificare la password. Obbligatorio"></span>
		    <input type="password" class="form-control" placeholder="Password" name="password" >
		  </div>
		  
		  <div class="form-group">
		    <label for="via">Via</label><span class="glyphicon glyphicon-question-sign" aria-hidden="true" data-toggle="tooltip" data-placement="right" title="Specificare la via. Obbligatorio"></span>
		    <input type="text" class="form-control" name="via">
		  </div>
		  
		  <div class="form-group">
		    <label for="civico">Civico</label><span class="glyphicon glyphicon-question-sign" aria-hidden="true" data-toggle="tooltip" data-placement="right" title="Specificare il numero civico."></span>
		    <input type="text" class="form-control" name="civico">
		  </div>
		  
		  <div class="form-group">
		    <label for="cap">Cap</label><span class="glyphicon glyphicon-question-sign" aria-hidden="true" data-toggle="tooltip" data-placement="right" title="Specificare il codice di avviamento postale. Obbligatorio"></span>
		    <input type="text" class="form-control" name="cap">
		  </div>
		  
		  <div class="form-group">
		    <label for="citta">Città</label><span class="glyphicon glyphicon-question-sign" aria-hidden="true" data-toggle="tooltip" data-placement="right" title="Specificare la propria città. Obbligatorio"></span>
		    <input type="text" class="form-control" name="citta">
		  </div>
		  
		  <div class="form-group">
		    <label for="provincia">Provincia</label><span class="glyphicon glyphicon-question-sign" aria-hidden="true" data-toggle="tooltip" data-placement="right" title="Specificare la propria provincia. Obbligatorio"></span>
		    <input type="text" class="form-control" name="provincia">
		  </div>
		  
		  <div class="checkbox">
		    <label>
		      <input type="checkbox"> Accetto i termini.
		    </label>
		  </div>
		  
		  <button type="submit" class="btn btn-default" name="mode" value="iscrizione">Iscrizione</button>
		</form>
		
		<% if(request.getAttribute("error") != null){ %>
		<br><p class="bg-danger"><%=request.getAttribute("error")%></p>
		<% } %>

      </div> <!-- /jumbotron -->

    </div> <!-- /container -->
    
<jsp:include page="footer.jsp" />