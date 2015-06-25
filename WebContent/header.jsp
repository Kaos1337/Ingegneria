<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<jsp:useBean id="utente" scope="session" class="it.univr.is.entity.Utente"></jsp:useBean> 
<%

String userEmail = null;

// Check if this is new comer on your web page.
if (session.isNew()){
	userEmail = utente.getEmail();
}

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="shortcut icon" href="./img/favicon.ico" />

<!-- jQuery -->
<script src="./js/jquery-2.1.4.min.js"></script>

<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">

<!-- Optional theme -->
<!-- <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap-theme.min.css">  -->

<!-- Latest compiled and minified JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>

<style type="text/css">

/* Sticky footer styles
-------------------------------------------------- */
html {
  position: relative;
  min-height: 100%;
}
body {
  /* Margin bottom by footer height */
  margin-bottom: 60px;
}
.footer {
  position: absolute;
  bottom: 0;
  width: 100%;
  /* Set the fixed height of the footer here */
  height: 60px;
  background-color: #f5f5f5;
}


/* Custom page CSS
-------------------------------------------------- */
/* Not required for template or sticky footer method. */

body > .container {
  padding: 60px 15px 0;
}
.container .text-muted {
  margin: 20px 0;
}

.footer > .container {
  padding-right: 15px;
  padding-left: 15px;
}

code {
  font-size: 80%;
}

</style>

</head>

<body>

    <nav class="navbar navbar-default navbar-fixed-top">
      <div class="container">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand" href="#">La Libreria</a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
          <ul class="nav navbar-nav">
            <!-- <li class="active"><a href="index.jsp">Home</a></li>  -->
            <li><a href="index.jsp">Home</a></li>
            
            <% if(userEmail != null){ %>
            <li><a href="#voce2">Pannello Utente</a></li>
            
            <li class="dropdown">
              <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Libri <span class="caret"></span></a>
              <ul class="dropdown-menu">
                <li><a href="#">Inserisci libro</a></li>
                <li><a href="#">Cerca libro</a></li>
                <li><a href="#">Ricerca</a></li>
                <li role="separator" class="divider"></li>
                <li class="dropdown-header">La tua libreria</li>
                <li><a href="#">Gestisci</a></li>
              </ul>
            </li>
            
            <% } %>
          </ul>
          <ul class="nav navbar-nav navbar-right">
          
          <% if(userEmail == null){ %>
          
            <li><a href="./login.jsp">Login</a></li>
            <li><a href="./register.jsp">Registrati</a></li>
            
          <% }else{  %>

			<li><a href="#">Ciao <%=utente.getNome()%>!</a></li>
  
          <% } %>
          </ul>
        </div><!--/.nav-collapse -->
      </div>
    </nav>