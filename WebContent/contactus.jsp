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
				<label for="nomecognome">Nome e Cognome</label><span class="glyphicon glyphicon-question-sign" aria-hidden="true" data-toggle="tooltip" data-placement="right" title="Specificare nome e cognome"></span>
				<% if(utente.getNome() == null && utente.getCognome() == null){ %>
					<input type="text" class="form-control" name="nomecognome" value="">
				<% } else { %>
					<input type="text" class="form-control" name="nomecognome" value="<%= utente.getNome() + " " + utente.getCognome() %>">
				<% } %>
			</div>
			
			<div class="form-group">
				<label for="email">Email</label><span class="glyphicon glyphicon-question-sign" aria-hidden="true" data-toggle="tooltip" data-placement="right" title="Specificare la mail a cui contattarti"></span>
				<% if(utente.getEmail() == null){ %>
					<input type="text"	class="form-control" name="email" value="">
				<% }else{ %>
					<input type="text"	class="form-control" name="email" value="<%= utente.getEmail() %>">
				<% } %>
			</div>
			
			<div class="form-group">
				<label for="oggetto">Oggetto</label><span class="glyphicon glyphicon-question-sign" aria-hidden="true" data-toggle="tooltip" data-placement="right" title="Dare un titolo al problema"></span>
					<input type="text" class="form-control" name="oggetto">
			</div>

			<div class="form-group">
				<label for="reparto">Reparto</label><span class="glyphicon glyphicon-question-sign" aria-hidden="true" data-toggle="tooltip" data-placement="right" title="-Tecnico per quanto riguarda malfunzionamenti. -Amministrativo per quanto riguarda i contenuti."></span>
				<select class="form-control" name="reparto">
					<option value="tecnico">Tecnico</option>
					<option value="amministrativo">Amministrativo</option>
				</select>
			</div>
			<div class="form-group">
				<label for="oggetto">Messaggio</label><span class="glyphicon glyphicon-question-sign" aria-hidden="true" data-toggle="tooltip" data-placement="right" title="Specificare dettagliatamente il problema"></span>
				<textarea class="form-control" rows="6" name="messaggio" value="<%= request.getParameter("message") %>"></textarea>
			</div>

			<button type="submit" class="btn btn-default" name="mode" value="contattaci">Invia</button>
		</form>
		
			<% if(request.getAttribute("error") != null){ %>
			<br><p class="bg-danger"><%=request.getAttribute("error")%></p>
			<% } %>

		<% } %>

	</div>
	<!-- /jumbotron -->

</div>
<!-- /container -->


<jsp:include page="footer.jsp" />