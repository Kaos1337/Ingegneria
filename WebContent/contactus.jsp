<jsp:useBean id="utente" scope="session" class="it.univr.is.entity.Utente"></jsp:useBean>
<jsp:include page="header.jsp" />

<head>
<title>Contattaci</title>
</head>


<div class="container">

	<div class="jumbotron">
		<h2>Contattaci</h2>

		<% if (request.getParameter("info") != null) { %>
		<br>
		<p class="bg-success"><%=request.getParameter("info")%></p>
		
		<% } else { %>


		<form action="MainServlet" method="POST">
			<div class="form-group">
				<label for="nomecognome">Nome e Cognome</label>
				<% if(utente.getNome() == null && utente.getCognome() == null){ %>
					<input type="text" class="form-control" name="nomecognome" value="">
				<% } else { %>
					<input type="text" class="form-control" name="nomecognome" value="<%= utente.getNome() + " " + utente.getCognome() %>">
				<% } %>
			</div>
			
			<div class="form-group">
				<label for="email">Email</label>
				<% if(utente.getEmail() == null){ %>
					<input type="text"	class="form-control" name="email" value="">
				<% }else{ %>
					<input type="text"	class="form-control" name="email" value="<%= utente.getEmail() %>">
				<% } %>
			</div>
			
			<div class="form-group">
				<label for="oggetto">Oggetto</label> <input type="text"
					class="form-control" name="oggetto">
			</div>

			<div class="form-group">
				<label for="reparto">Reparto</label> <select class="form-control" name="reparto">
					<option value="tecnico">Tecnico</option>
					<option value="amministrativo">Amministrativo</option>
				</select>
			</div>
			<div class="form-group">
				<label for="oggetto">Messaggio</label>
				<textarea class="form-control" rows="6" name="messaggio"></textarea>
			</div>

			<button type="submit" class="btn btn-default" name="mode" value="contattaci">Invia</button>
		</form>

		<% } %>

	</div>
	<!-- /jumbotron -->

</div>
<!-- /container -->


<jsp:include page="footer.jsp" />