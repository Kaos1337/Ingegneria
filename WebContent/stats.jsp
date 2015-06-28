<jsp:useBean id="utente" scope="session" class="it.univr.is.entity.Utente"></jsp:useBean>
<jsp:include page="header.jsp" />

<head>
<title>Statistiche</title>

<script type="text/javascript" src="https://www.google.com/jsapi"></script>

<script type="text/javascript">
      google.load("visualization", "1", {packages:["corechart"]});
      google.setOnLoadCallback(drawChart);
      function drawChart() {
        var data = google.visualization.arrayToDataTable([
          ['Year', 'Sales', 'Expenses'],
          ['2013',  1000,      400],
          ['2014',  1170,      460],
          ['2015',  660,       1120],
          ['2016',  1030,      540]
        ]);

        var options = {
          title: 'Company Performance',
          hAxis: {title: 'Year',  titleTextStyle: {color: '#333'}},
          vAxis: {minValue: 0}
        };

        var chart = new google.visualization.AreaChart(document.getElementById('chart_div'));
        chart.draw(data, options);
      }
    </script>
    
</head>

<% if(session.getAttribute("id") == null && utente.getRuolo() > 0){ %>
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