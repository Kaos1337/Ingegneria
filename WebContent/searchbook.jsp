<jsp:useBean id="utente" scope="session" class="it.univr.is.entity.Utente"></jsp:useBean>
<jsp:include page="header.jsp" />
<%@page import="java.util.*"%>
<%@page import="it.univr.is.entity.LibroUtente"%>

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
		    <label for="categoria">Prima Categoria</label>
		    <select class="form-control" name="categoria">
				<option value="scienza">Scienza</option>
				<option value="filosofia">Filosofia</option>
				<option value="giallo">Giallo</option>
				<option value="fantasy">Fantasy</option>
				<option value="romanzo">Romanzo</option>
			</select>
		  </div>
		  
		  <div class="form-group">
		    <label for="categoria2">Seconda Categoria</label>
		    <select class="form-control" name="categoria2">
				<option value="scienza">Scienza</option>
				<option value="filosofia">Filosofia</option>
				<option value="giallo">Giallo</option>
				<option value="fantasy">Fantasy</option>
				<option value="romanzo">Romanzo</option>
			</select>
		  </div>
		  
		  <div class="form-group">
		    <label for="edizione">Edizione</label>
		    <input type="text" class="form-control" name="edizione">
		  </div>
		  
		  <div class="form-group">
		    <label for="isbn">ISBN</label>
		    <input type="text" class="form-control" name="isbn">
		  </div>
		  
		  <div class="form-group">
		    <label for="nome">Nome utente</label>
		    <input type="text" class="form-control" name="nome">
		  </div>
		  
		  <div class="form-group">
		    <label for="citta">Citt�</label>
		    <input type="text" class="form-control" name="citta">
		  </div>
		  
		  <div class="form-group">
		    <label for="provincia">Provincia</label>
		    <input type="text" class="form-control" name="provincia">
		  </div>
		  
		  <div class="form-group">
		    <label for="km">Km</label>
		    <input type="text" class="form-control" name="km">
		  </div>
		  
		  <button type="submit" class="btn btn-default" name="mode" value="cerca_libro">Cerca</button>
		</form>
		
		
		<% ArrayList<LibroUtente> al = new ArrayList<LibroUtente>(); 
        
        if(request.getAttribute("lista_libri") == null){
        %>
        Cerca un libro con il form qua sopra.
		<% }else{ %>
        
	        <form class="form-inline" action="MainServlet" method="POST">
	        
			  <table class="table table-striped">
			  
			  		<tr>
			  			<th>Selezione</th>
			  			<th>Titolo</th>
			  			<th>Autore</th>
			  			<th>Categoria</th>
			  			<th>Categoria2</th>
			  			<th>Edizione</th>
			  			<th>ISBN</th>
			  			<th>Copertina</th>
			  		</tr>
			  		<% 
					al = (ArrayList<LibroUtente>) request.getAttribute("lista_libri"); 
			  		for(int i = 0; i < al.size(); i++){ 
			  			LibroUtente libroutente = (LibroUtente) al.get(i);
					
					%>
			  		<tr>

						<td>
						<div class="checkbox">
							<label>
								<input type="checkbox" name="selezione" value="<%= libroutente.getTitolo() %>">
							</label>
						</div>
						</td>
						
						<td>
						<div class="form-group">
							<%= libroutente.getTitolo() %>
						</div>
						</td>
						  
						<td>
						<div class="form-group">
						    <%= libroutente.getAutore() %>
						</div>
						</td>
						  
						<td>
						<div class="form-group">
						    <%= libroutente.getCategoria() %>
						</div>
						</td>
						
						<td>
						<div class="form-group">
							<%= libroutente.getCategoria2() %>
						</div>
						</td>
						
						<td>
						<div class="form-group">
						    <%= libroutente.getEdizione() %>
						</div>
						</td>
						
						<td>
						<div class="form-group">
							<%= libroutente.getIsbn() %>
						</div>
						</td>
						
						<td>
						<div class="form-group">
							<%= libroutente.getCopertina() %>
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