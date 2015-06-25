<jsp:include page="header.jsp" />

<head>
<title>Inserisci libro</title>
</head>
	
	<div class="container">

      <div class="jumbotron">
        <h2>Inserisci libro</h2>
        
        <form action="MainServlet" method="POST">
		  
		<div class="form-group">
		    <label for="titolo">Titolo</label>
		    <input type="text" class="form-control" name="titolo">
		</div>
		  
		<div class="form-group">
		    <label for="autore">Autore</label>
		    <input type="email" class="form-control" name="autore">
		</div>
		  
		<div class="form-group">
		    <label for="categoria">Prima Categoria</label>
		    <input type="text" class="form-control" name="categoria">
		</div>
		  
		<div class="form-group">
		    <label for="categoria2">Seconda Categoria</label>
			<input type="text" class="form-control" name="categoria2">
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
			<label for="copertina">Copertina</label>
			<input type="file" id="copertina">
			<p class="help-block">Inserisci la copertina del libro</p>
		</div>
		  
		  <button type="submit" class="btn btn-default" name="mode" value=inserimento_libro>Aggiungi</button>
		</form>

      </div> <!-- /jumbotron -->

    </div> <!-- /container -->
    
<jsp:include page="footer.jsp" />