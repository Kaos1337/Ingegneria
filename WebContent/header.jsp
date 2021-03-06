<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<jsp:useBean id="utente" scope="session" class="it.univr.is.entity.Utente"></jsp:useBean>

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

<!-- intro.js -->
<script src="http://usablica.github.io/intro.js/intro.js"></script>
<link href="http://usablica.github.io/intro.js/introjs.css" rel="stylesheet">

<script type="text/javascript">


  
$( document ).ready(function(){
	
	function startIntro(){
	    var intro = introJs();
	      intro.setOptions({
	        steps: [
	          {
	            element: "#data-step1",
	            intro: "Questo � il menu principale, da qua potrai navigare in tutte le pagine del sito.",
	            position: 'bottom'
	          },
	          {
	            element: '#data-step2',
	            intro: "Da qua potrai andare alla home page.",
	            position: 'bottom'
	          },
	          {
		        element: "#data-step3",
		        intro: "Da qua potrai accedere al tuo pannello utente.",
		        position: 'bottom'
		      },
		      {
		        element: "#data-step4",
		        intro: "Questa voce porta ad un sottomenu per i libri.",
		        position: 'right'
		      },
		      {
		        element: "#data-step5",
		        intro: "Da qua potrai andare alla pagina per inserire un libro.",
		        position: 'right'
		      },
		      {
		        element: "#data-step6",
		        intro: "Da qua potrai andare alla pagina per cercare un libro.",
		        position: 'right'
		      },
		      {
		        element: "#data-step7",
		        intro: "Da qua potrai andare nella pagina di gestione dei tuoi libri.",
		        position: 'right'
		      },
		      {
		        element: "#data-step8",
		        intro: "In questa sezione del menu puoi gestire lo stato di login del tuo account.",
		        position: 'left'
		      },
		      
	        ]
	      });

	      intro.onbeforechange(function(element) {
	        if (this._currentStep === 3 ||
	        	this._currentStep === 4 ||
	        	this._currentStep === 5 ||
	        	this._currentStep === 6) {
	          setTimeout(function() {
	            $(".dropdown").addClass("open");
	          });
	        }
	        
	        if (this._currentStep === 7) {
		          setTimeout(function() {
		            $(".dropdown").removeClass("open");
		          });
		    }
	        
	      });
	      intro.setOption('showProgress', true).start();
	  };
	  
	$('[data-toggle="tooltip"]').tooltip();
	$('[data-toggle="popover"]').popover();
	
	$("#help").click(function (e) {
		startIntro();
	});
	
});

</script>

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

/* Per il bottone di logout
-------------------------------------------------- */
.navbar-default .navbar-nav>li>form>button {
  color: #777;
  display: block;
  padding: 10px 15px;
  padding-top: 15px;
  padding-bottom: 15px;
  background:none!important;
  border:none;
  font: inherit;
}

.navbar-default .navbar-nav>li>form>button:focus, .navbar-default .navbar-nav>li>form>button:hover {
  color: #333;
  background-color: transparent;
}

/*.nav>li>form>a:focus, .nav>li>form>button:hover {
  text-decoration: none;
  background-color: #eee;
}*/

/* Per il bottone di gestione
-------------------------------------------------- */
.dropdown-menu>li>form>button {

  display: block;
  padding: 3px 20px;
  clear: both;
  font-weight: 400;
  line-height: 1.42857143;
  color: #333;
  white-space: nowrap;
  background:none!important;
  border:none;
  font: inherit;
  width: 100%;
  text-align: left;
}

.dropdown-menu>li>form>button:focus, .dropdown-menu>li>form>button:hover {
  color: #262626;
  text-decoration: none;
  background-color: #f5f5f5!important;
}

/*.nav>li>form>a:focus, .nav>li>form>button:hover {
  text-decoration: none;
  background-color: #eee;
}*/

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
          <a class="navbar-brand">La Libreria</a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
          <ul class="nav navbar-nav" id="data-step1">
            <!-- <li class="active"><a href="index.jsp">Home</a></li>  -->
            <li><a href="index.jsp" id="data-step2">Home</a></li>
            
            <% if(session.getAttribute("id") != null){ %>
	            <li><a href="./ucp.jsp" id="data-step3">Pannello Utente</a></li>
	            
	            <li class="dropdown" id="data-step4">
	              <a href="#" id="menu_libri" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Libri <span class="caret"></span></a>
	              <ul class="dropdown-menu">
	                <li><a href="./insertbook.jsp" id="data-step5">Inserisci libro</a></li>
	                <li><a href="./searchbook.jsp" id="data-step6">Cerca libro</a></li>
	                <li role="separator" class="divider"></li>
	                <li class="dropdown-header" >La tua libreria</li>
	                <li><form action="MainServlet"><button id="data-step7" type="submit" name="mode" value="libreria">Gestisci</button></form></li>
	              </ul>
	            </li>
            	<% if(utente.getRuolo() > 0){ %>
            		<li><form action="MainServlet"><button type="submit" name="mode" value="statistiche">Statistiche</button></form></li>
            	<% } %>
            	<li><a><span id="help" class="glyphicon glyphicon-question-sign" aria-hidden="true"></span></a></li>
            <% } %>
          </ul>
          <ul class="nav navbar-nav navbar-right" id="data-step8">
          
          <% if(session.getAttribute("id") == null){ %>
          
            <li><a href="./login.jsp">Login</a></li>
            <li><a href="./register.jsp">Registrati</a></li>
            
          <% }else{  %>

			<li><a href="./ucp.jsp">Ciao <%=utente.getNome()%>!</a></li>
			<li><form action="MainServlet"><button type="submit" name="mode" value="logout">Logout</button></form></li>
			
  
          <% } %>
          </ul>
        </div><!--/.nav-collapse -->
      </div>
    </nav>
