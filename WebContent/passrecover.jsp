<jsp:useBean id="utente" scope="session" class="it.univr.is.entity.Utente"></jsp:useBean>
<jsp:include page="header.jsp" />

<% if(session.getAttribute("id") != null){
	response.setStatus(response.SC_MOVED_TEMPORARILY);
	response.setHeader("Location", "index.jsp");
}%>

<head>
<title>Recupero Password</title>
</head>

	<div class="container">

      <!-- Main component for a primary marketing message or call to action -->
      <div class="jumbotron">
        <h2>Recupero password</h2>
        
		<form action="MainServlet" method="POST">
		
		  <div class="form-group">
		    <label for="email">Email</label>
		    <input type="email" class="form-control" name="email">
		  </div>
		  
		  <button type="submit" class="btn btn-default" name="mode" value="recupero_psw">Recupera</button>
		</form>

      </div> <!-- /jumbotron -->

    </div> <!-- /container -->
    
<jsp:include page="footer.jsp" />