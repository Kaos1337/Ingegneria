<jsp:useBean id="utente" scope="session" class="it.univr.is.entity.Utente"></jsp:useBean>
<jsp:include page="header.jsp" />

<head>
<title>Statistiche</title>

<% if(session.getAttribute("id") != null && utente.getRuolo() > 0){ %>

<script type="text/javascript" src="https://www.google.com/jsapi"></script>

<script type="text/javascript">
      google.load("visualization", "1", {packages:["corechart"]});
      google.setOnLoadCallback(drawChart);
      function drawChart() {
        var data = google.visualization.arrayToDataTable([
          ['Data', 'Iscrizioni', 'Prestiti'],       
          
          <% String[][] arrayBid = (String[][]) request.getAttribute("dati_mensili");
          
			for(int i = 0; i < arrayBid.length; i++){ %>
        	  	<%= "['" + arrayBid[i][0] + "'," + arrayBid[i][1] + "," + arrayBid[i][2] + "]," %>
			<% } %>
          
        ]);

        var options = {
          title: 'Statistiche',
          hAxis: {title: 'Giorni',  titleTextStyle: {color: '#333'}},
          vAxis: {minValue: 0}
        };

        var chart = new google.visualization.AreaChart(document.getElementById('chart_div'));
        chart.draw(data, options);
      }
    </script>
    
<% }  %>
</head>

<% if(session.getAttribute("id") == null && utente.getRuolo() < 1){ %>
<div class="container">
	<p class="bg-danger">Errore: Per vedere questa pagina bisogna essere loggati come amministratori.</p>
</div>
<% }else{  %>

	<div class="container">

      <div class="jumbotron">
        
        <div id="chart_div" style="width: 900px; height: 500px;"></div>

      </div> <!-- /jumbotron -->

    </div> <!-- /container -->
    
<% } %>
    
<jsp:include page="footer.jsp" />