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
		    <label for="nome">Nome</label> <span class="glyphicon glyphicon-question-sign" aria-hidden="true" data-toggle="tooltip" data-placement="right" title="Specificare il proprio nome. Obbligatorio"></span>
		    <!-- <input type="email" class="form-control" id="exampleInputEmail1" placeholder="Email">  -->
		    <input type="text" class="form-control" id="inputNome" name="nome" <%if (request.getParameter("nome") != null){%> value="<%=request.getParameter("nome")%>"<%}%>>
		  </div>
		  
		  <div class="form-group">
		    <label for="cognome">Cognome</label> <span class="glyphicon glyphicon-question-sign" aria-hidden="true" data-toggle="tooltip" data-placement="right" title="Specificare il proprio cognome. Obbligatorio"></span>
		    <input type="text" class="form-control" name="cognome" <%if (request.getParameter("cognome") != null){%> value="<%=request.getParameter("cognome")%>"<%}%>>
		  </div>
		  
		  <div class="form-group">
		    <label for="email">Email</label> <span class="glyphicon glyphicon-question-sign" aria-hidden="true" data-toggle="tooltip" data-placement="right" title="Specificare la propria email. Obbligatorio"></span>
		    <input type="email" class="form-control" name="email" <%if (request.getParameter("nome") != null){%> value="<%=request.getParameter("email")%>"<%}%>>
		  </div>
		  
		  <div class="form-group">
		    <label for="password">Password</label> <span class="glyphicon glyphicon-question-sign" aria-hidden="true" data-toggle="tooltip" data-placement="right" title="Specificare la password. Obbligatorio"></span>
		    <input type="password" class="form-control" placeholder="Password" name="password">
		  </div>
		  
		  <div class="form-group">
		    <label for="via">Via</label> <span class="glyphicon glyphicon-question-sign" aria-hidden="true" data-toggle="tooltip" data-placement="right" title="Specificare la via. Obbligatorio"></span>
		    <input type="text" class="form-control" name="via" <%if (request.getParameter("via") != null){%> value="<%=request.getParameter("via")%>"<%}%>>
		  </div>
		  
		  <div class="form-group">
		    <label for="civico">Civico</label> <span class="glyphicon glyphicon-question-sign" aria-hidden="true" data-toggle="tooltip" data-placement="right" title="Specificare il numero civico."></span>
		    <input type="text" class="form-control" name="civico" <%if (request.getParameter("civico") != null){%> value="<%=request.getParameter("civico")%>"<%}%>>
		  </div>
		  
		  <div class="form-group">
		    <label for="cap">Cap</label> <span class="glyphicon glyphicon-question-sign" aria-hidden="true" data-toggle="tooltip" data-placement="right" title="Specificare il codice di avviamento postale. Obbligatorio"></span>
		    <input type="text" class="form-control" name="cap" <%if (request.getParameter("cap") != null){%> value="<%=request.getParameter("cap")%>"<%}%>>
		  </div>
		  
		  <div class="form-group">
		    <label for="citta">Citt�</label> <span class="glyphicon glyphicon-question-sign" aria-hidden="true" data-toggle="tooltip" data-placement="right" title="Specificare la propria citt�. Obbligatorio"></span>
		    <input type="text" class="form-control" name="citta" <%if (request.getParameter("citta") != null){%> value="<%=request.getParameter("citta")%>"<%}%>>
		  </div>
		  
		  <div class="form-group">
		    <label for="provincia">Provincia</label> <span class="glyphicon glyphicon-question-sign" aria-hidden="true" data-toggle="tooltip" data-placement="right" title="Specificare la propria provincia. Obbligatorio"></span>
		    <input type="text" class="form-control" name="provincia" <%if (request.getParameter("provincia") != null){%> value="<%=request.getParameter("provincia")%>"<%}%>>
		  </div>
		  
		  <div class="checkbox">
		    <label>
		      <input type="checkbox" name="termini"> Accetto i termini.
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