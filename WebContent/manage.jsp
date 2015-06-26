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
        <h2>Inserisci libro</h2>      
        
        <% ArrayList<Libro> al = new ArrayList<Libro>(); 
        
        if(al.isEmpty() || al == null){
        %>
        Nessun libro presente.
		<% }else{ %>
        
	        <form class="form-inline" action="MainServlet" method="POST">
	        
	        
			<% 
			al = (ArrayList<Libro>) request.getAttribute("lista_libri"); 
			for(int i = 0; i < al.size(); i++){ %>
			
			<% Libro libro = (Libro) al.get(i);
			String select[] = request.getParameterValues("selezione");
			%>
			
			<div class="checkbox">
				<label>
					<input type="checkbox" name="selezione" value="<%= libro.getTitolo() %>"> Seleziona
				</label>
			</div>
			
			<div class="form-group">
			    <label for="titolo">Titolo</label>
				<%= libro.getTitolo() %>
			</div>
			  
			<div class="form-group">
			    <label for="autore">Autore</label>
			    <%= libro.getAutore() %>
			</div>
			  
			<div class="form-group">
			    <label for="categoria">Prima Categoria</label>
			    <%= libro.getCategoria() %>
			</div>
			  
			<div class="form-group">
			    <label for="categoria2">Seconda Categoria</label>
				<%= libro.getCategoria2() %>
			</div>
			  
			<div class="form-group">
			    <label for="edizione">Edizione</label>
			    <%= libro.getEdizione() %>
			</div>
			  
			<div class="form-group">
			    <label for="isbn">ISBN</label>
				<%= libro.getIsbn() %>
			</div>
			  
			<div class="form-group">
				<label for="copertina">Copertina</label>
				<%= libro.getCopertina() %>
			</div>
			
			<%}%>
			  
			  <button type="submit" class="btn btn-default" name="mode" value=inserimento_libro>Aggiungi</button>
			</form>
		
		<% } %>

      </div> <!-- /jumbotron -->

    </div> <!-- /container -->
    
<% } %>
    
<jsp:include page="footer.jsp" />