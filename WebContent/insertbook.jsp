<jsp:useBean id="utente" scope="session" class="it.univr.is.entity.Utente"></jsp:useBean>
<jsp:include page="header.jsp" />

<head>
<title>Inserisci libro</title>
</head>
	
<% if(session.getAttribute("id") == null){ %>
<div class="container">
	<p class="bg-danger">Errore: Per vedere questa pagina bisogna essere loggati.</p>
</div>
<% }else{  %>

	<div class="container">

      <div class="jumbotron">
        <h2>Inserisci libro</h2><a class="btn btn-medium btn-info" style="float: right; margin: -40px 0px;" href="javascript:void(0);" onclick="javascript:introJs().start();">Aiuto</a>
        
        <form action="MainServlet" method="POST">
		  
		<div class="form-group" data-step="1" data-intro="Qua si inserisce il titolo del libro.">
		    <label for="titolo">Titolo</label> <span class="glyphicon glyphicon-question-sign" aria-hidden="true" data-toggle="tooltip" data-placement="top" title="Specificare il titolo del libro. Obbligatorio"></span>
		    <input type="text" class="form-control" name="titolo">
		</div>
		  
		<div class="form-group" data-step="2" data-intro="Qua si inserisce l'autore del libro.">
		    <label for="autore">Autore</label> <span class="glyphicon glyphicon-question-sign" aria-hidden="true" data-toggle="tooltip" data-placement="top" title="Specificare l'autore del libro. Obbligatorio"></span>
		    <input type="text" class="form-control" name="autore">
		</div>
		  
		<div class="form-group" data-step="3" data-intro="Qua si seleziona la prima categoria del libro.">
		    <label for="categoria">Prima Categoria</label> <span class="glyphicon glyphicon-question-sign" aria-hidden="true" data-toggle="tooltip" data-placement="top" title="Specificare una categoria. Obbligatorio"></span>
		     <select class="form-control" name="categoria">
				<option value="scienza">Scienza</option>
				<option value="filosofia">Filosofia</option>
				<option value="giallo">Giallo</option>
				<option value="fantasy">Fantasy</option>
				<option value="romanzo">Romanzo</option>
			</select>
		</div>
		  
		<div class="form-group" data-step="4" data-intro="Qua si seleziona la seconda categoria del libro.">
		    <label for="categoria2">Seconda Categoria</label> <span class="glyphicon glyphicon-question-sign" aria-hidden="true" data-toggle="tooltip" data-placement="top" title="Specificare una seconda categoria"></span>
			 <select class="form-control" name="categoria2">
			 	<option value=""></option>
				<option value="scienza">Scienza</option>
				<option value="filosofia">Filosofia</option>
				<option value="giallo">Giallo</option>
				<option value="fantasy">Fantasy</option>
				<option value="romanzo">Romanzo</option>
			</select>
		</div>
		  
		<div class="form-group" data-step="5" data-intro="Qua si inserisce l'edizione del libro.">
		    <label for="edizione">Edizione</label> <span class="glyphicon glyphicon-question-sign" aria-hidden="true" data-toggle="tooltip" data-placement="top" title="Specificare edizione del libro"></span>
		    <input type="text" class="form-control" name="edizione">
		</div>
		  
		<div class="form-group" data-step="6" data-intro="Qua si inserisce l'ISBN del libro.">
		    <label for="isbn">ISBN</label> <span class="glyphicon glyphicon-question-sign" aria-hidden="true" data-toggle="tooltip" data-placement="top" title="Specificare l'ISBN del libro"></span>
			<input type="text" class="form-control" name="isbn">
		</div>
		  
		<div class="form-group" data-step="7" data-intro="Qua si inserisce la copertina del libro.">
			<label for="copertina">Copertina</label> <span class="glyphicon glyphicon-question-sign" aria-hidden="true" data-toggle="tooltip" data-placement="top" title="Assegna una copertina"></span>
			<input type="file" id="copertina">
			<p class="help-block">Inserisci la copertina del libro</p>
		</div>
		  
		  <button type="submit" class="btn btn-default" name="mode" data-step="8" data-intro="Infine si deve premere questo bottone per confermare." value=inserimento_libro>Aggiungi</button>
		</form>

      </div> <!-- /jumbotron -->

    </div> <!-- /container -->
    
<% } %>
    
<jsp:include page="footer.jsp" />