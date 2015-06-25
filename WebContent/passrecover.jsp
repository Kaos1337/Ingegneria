<jsp:include page="header.jsp" />

<head>
<title>Recupero Password</title>
</head>

	<div class="container">

      <!-- Main component for a primary marketing message or call to action -->
      <div class="jumbotron">
        <h2>Recupero password</h2>
        
		<form action="MainServlet" method="POST">
		
		  <div class="form-group">
		    <label for="cognome">Email</label>
		    <input type="email" class="form-control" name="email">
		  </div>
		  
		  <button type="submit" class="btn btn-default" name="mode" value="recupero_psw">Recupera</button>
		</form>
		
		<%
		if(request.getAttribute("info") != null){
		%>
		<br><p class="bg-success"><%=request.getAttribute("info")%></p>
		<% } %>

      </div> <!-- /jumbotron -->

    </div> <!-- /container -->
    
<jsp:include page="footer.jsp" />