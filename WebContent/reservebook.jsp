<jsp:useBean id="utente" scope="session" class="it.univr.is.entity.Utente"></jsp:useBean>
<jsp:include page="header.jsp" />

<head>
<title>Contatta Utente</title>
</head>

<% if(session.getAttribute("id") == null){ %>
<div class="container">
	<p class="bg-danger">Errore: Per vedere questa pagina bisogna essere loggati.</p>
</div>
<% }else{  %>

	<div class="container">

      <div class="jumbotron">
        <h2>Contatta l'utente per il libro</h2>
        
        <% if(request.getParameter("info") != null){ %>
		<br><p class="bg-success"><%=request.getParameter("info")%></p>
		<% } else { %>
		
		
		<form action="MainServlet" method="POST">
		  <div class="form-group">
		    <label for="oggetto">Oggetto</label>
		    <input type="text" class="form-control" name="oggetto">
		  </div>
		  
		   <div class="form-group">
		    <label for="messaggio">Messaggio</label>
		    <textarea class="form-control" rows="6" name="messaggio"></textarea>
		  </div>
		  
		  <button type="submit" class="btn btn-default" name="mode" value="manda_messaggio">Invia</button>
		</form>
		
			<% if(request.getAttribute("error") != null){ %>
			<br><p class="bg-danger"><%=request.getAttribute("error")%></p>
			<% } %>
		
		<% } %>
		

      </div> <!-- /jumbotron -->

    </div> <!-- /container -->
    
<% } %>
    
<jsp:include page="footer.jsp" />