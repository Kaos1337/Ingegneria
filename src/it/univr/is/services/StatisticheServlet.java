package it.univr.is.services;

import it.univr.is.entity.Utente;
import it.univr.is.support.EntityFactory;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet dedicata alle operazioni che coinvolgono statistiche
 * @author marco
 *
 */
@WebServlet("/StatisticheServlet")
public class StatisticheServlet extends AbstractServlet {
	private static final long serialVersionUID = 1L;
       
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StatisticheServlet() {
        super();
       
    }

    /**
     * Metodo per determinare l'operazione da eseguire
     */
	@Override
	void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String tipointerrogazione = request.getParameter("mode");
		
		switch(tipointerrogazione){
		
		case "statistiche":
			this.getDati(request,response);
			break;
		default :
			request.setAttribute("error", "Valore non gestito: "+ tipointerrogazione);
			request.getRequestDispatcher("error.jsp").forward(request, response);
		
		}
	

	
	
}

	private void getDati(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		ArrayList<String> trenta_giorni_da_oggi = this.getDate();
		
		////////TEMP
		
		//request.setAttribute("dati_assoluti", ds.getStatAssolute());
		//request.setAttribute("dati_mensili", ds.getStatMensili(trenta_giorni_da_oggi););
		
		String[][] mensili = {{"2013/1/1",  "10",   "4"},
								{"2013/1/2",  "10",   "40"},
								{"2013/1/3",  "1",   "5"},
								{"2013/1/4",  "0",   "0"},
								{"2013/1/5",  "0",   "0"},
								{"2013/1/6",  "19",   "20"},
								{"2013/1/7",  "7",   "13"},
								{"2013/1/8",  "4",   "16"},
								{"2013/1/9",  "4",   "7"},
								{"2013/1/10",  "10",   "30"}};
								

        //request.setAttribute("dati_assoluti", assoluti);
        request.setAttribute("dati_mensili", mensili);
        		
		
		////////TEMP
		request.getRequestDispatcher("stats.jsp").forward(request, response);
	
		
	}
	
	private static ArrayList<String> getDate(){
	    GregorianCalendar cal = new GregorianCalendar();
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
	    ArrayList<String> dates = new ArrayList<String>();
	    
	    int day = cal.get(GregorianCalendar.DAY_OF_MONTH);
	    int month = cal.get(GregorianCalendar.MONTH);
	    int year = cal.get(GregorianCalendar.YEAR);

	        for(int i=day; i > (day-5); i--){
	            cal.set(year, month, i);

	            Date date = cal.getTime();
	            dates.add(sdf.format(date));   
	        }
	    return dates;

	}
	
	
	
}
