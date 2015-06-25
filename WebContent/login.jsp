<jsp:include page="header.jsp" />

<head>
<title>Login</title>
</head>

	<div class="container">

      <!-- Main component for a primary marketing message or call to action -->
      <div class="jumbotron">
        <h2>Loggati</h2>
        
		<form action="MainServlet" method="POST">
		
		  <div class="form-group">
		    <label for="cognome">Email</label>
		    <input type="email" class="form-control" name="email">
		  </div>
		  
		  <div class="form-group">
		    <label for="cognome">Password</label>
		    <input type="password" class="form-control" placeholder="Password" name="password" >
		  </div>
		  
		  <button type="submit" class="btn btn-default" name="mode" value="login">Login</button>
		</form>
		
		<%
		if(request.getAttribute("error") != null){
		%>
		<br><p class="bg-danger"><%=request.getAttribute("error")%></p>
		<% } %>

      </div> <!-- /jumbotron -->

    </div> <!-- /container -->
    
<jsp:include page="footer.jsp" />