<jsp:useBean id="utente" scope="session" class="it.univr.is.entity.Utente"></jsp:useBean>
<jsp:include page="header.jsp" />

<% if(utente.getEmail() != null){
	response.setStatus(response.SC_MOVED_TEMPORARILY);
	response.setHeader("Location", "index.jsp");
}%>

<head>
<title>Login</title>
</head>

	<div class="container">

      <!-- Main component for a primary marketing message or call to action -->
      <div class="jumbotron">
        <h2>Loggati</h2>
        
		<form action="MainServlet" method="POST">
		
		  <div class="form-group">
		    <label for="email">Email</label>
		    <input type="email" class="form-control" name="email">
		  </div>
		  
		  <div class="form-group">
		    <label for="password">Password</label>
		    <input type="password" class="form-control" placeholder="Password" name="password" >
		  </div>
		  
		  <button type="submit" class="btn btn-default" name="mode" value="login">Login</button>
		  <a class="btn btn-primary" href="passrecover.jsp">Recupero password</a>
		</form>
		
		<% if(request.getAttribute("error") != null){ %>
		<br><p class="bg-danger"><%=request.getAttribute("error")%></p>
		<% } %>
		
		<% if(request.getAttribute("info") != null){ %>
		<br><p class="bg-success"><%=request.getAttribute("info")%></p>
		<% } %>

      </div> <!-- /jumbotron -->

    </div> <!-- /container -->
    
<jsp:include page="footer.jsp" />