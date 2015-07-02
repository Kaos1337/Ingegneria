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
          title: 'Statistiche Ultimi 30g',
          hAxis: {title: 'Giorni',  titleTextStyle: {color: '#333'}},
          vAxis: {minValue: 0}
        };

        var chart = new google.visualization.AreaChart(document.getElementById('chart_div'));
        chart.draw(data, options);
      }
</script>

<script type="text/javascript">
      google.load("visualization", "1", {packages:["corechart"]});
      google.setOnLoadCallback(drawChart);
      function drawChart() {
        var data = google.visualization.arrayToDataTable([
          ['Data', 'Iscrizioni', 'Prestiti'],       
          
          <% String[][] arrayBid2 = (String[][]) request.getAttribute("dati_assoluti");
          
			for(int i = 0; i < arrayBid[0].length; i++){ %>
        	  	<%= "['" + arrayBid2[0][i] + "'," + arrayBid2[1][i] + "," + arrayBid2[2][i] + "]," %>
			<% } %>
          
        ]);

        var options = {
          title: 'Statistiche Globali',
          hAxis: {title: 'Giorni',  titleTextStyle: {color: '#333'}},
          vAxis: {minValue: 0}
        };

        var chart = new google.visualization.AreaChart(document.getElementById('chart_div2'));
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
        
        <div id="chart_div" style="width: 900px; height: 500px; margin: auto;"></div>
        <br>
        <div id="chart_div2" style="width: 900px; height: 500px; margin: auto;"></div>

      </div> <!-- /jumbotron -->

    </div> <!-- /container -->
    
<% } %>
    
<jsp:include page="footer.jsp" />