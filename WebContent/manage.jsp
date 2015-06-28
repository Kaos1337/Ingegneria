<jsp:useBean id="utente" scope="session" class="it.univr.is.entity.Utente"></jsp:useBean>
<jsp:include page="header.jsp" />
<%@page import="java.util.*"%>
<%@page import="it.univr.is.entity.Libro"%>

<head>
<title>Gestisci libri</title>
</head>
	
<% if(session.getAttribute("id") == null){ %>
<div class="container">
	<p class="bg-danger">Errore: Per vedere questa pagina bisogna essere loggati.</p>
</div>
<% }else{  %>

	<div class="container">

      <div class="jumbotron">
        <h2>Gestione libreria</h2>      
        
        <% ArrayList<Libro> al = new ArrayList<Libro>(); 
        
        if(request.getAttribute("lista_libri") == null){
        %>
        Nessun libro presente.
		<% }else{ %>
        
	        <form class="form-inline" action="MainServlet" method="POST">
	        
	        <button type="submit" class="btn btn-default" name="mode" value="elimina_libro">Elimina libro/i</button>
			  <button type="submit" class="btn btn-default" name="mode" value="disponibile_libro">Rendi disponibile/i</button>
			  <button type="submit" class="btn btn-default" name="mode" value="occupato_libro">Rendi occupato/i</button>
			  <button type="submit" class="btn btn-default" name="mode" value="prenotato_libro">Rendi prenotato/i</button>
			  <br><br>
			  
			  
			  <table class="table table-striped">
			  
			  		<tr>
			  			<th>Selezione</th>
			  			<th>Stato</th>
			  			<th>Titolo</th>
			  			<th>Autore</th>
			  			<th>Categoria</th>
			  			<th>Categoria2</th>
			  			<th>Edizione</th>
			  			<th>ISBN</th>
			  			<th>Copertina</th>
			  		</tr>
			  		<% 
					al = (ArrayList<Libro>) request.getAttribute("lista_libri"); 
			  		for(int i = 0; i < al.size(); i++){ 
						Libro libro = (Libro) al.get(i);
					
					%>
			  		<tr>

						<td>
						<div class="checkbox">
							<label>
								<input type="checkbox" name="selezione" value="<%= libro.getTitolo() %>">
							</label>
						</div>
						</td>
						
						<td>
						<div class="form-group">
							<% if(libro.getStato() == 0){ %>
							Disponibile
							<% } else if(libro.getStato() == 1){ %>
							In prestito
							<% } else if(libro.getStato() == 2){ %>
							Non Disponibile
							<% } %>
						</div>
						</td>
						
						<td>
						<div class="form-group">
							<%= libro.getTitolo() %>
						</div>
						</td>
						  
						<td>
						<div class="form-group">
						    <%= libro.getAutore() %>
						</div>
						</td>
						  
						<td>
						<div class="form-group">
						    <%= libro.getCategoria() %>
						</div>
						</td>
						
						<td>
						<div class="form-group">
							<%= libro.getCategoria2() %>
						</div>
						</td>
						
						<td>
						<div class="form-group">
						    <%= libro.getEdizione() %>
						</div>
						</td>
						
						<td>
						<div class="form-group">
							<%= libro.getIsbn() %>
						</div>
						</td>
						
						<td>
						<div class="form-group">
							<%= libro.getCopertina() %>
						</div>
						</td>
					
					</tr>
				
				<% } %> <!-- ciclo libri -->
				
				</table>
				  
			</form>
		
		<% } %> <!-- se 0 libri -->

      </div> <!-- /jumbotron -->

    </div> <!-- /container -->
    
<% } %>
    
<jsp:include page="footer.jsp" />