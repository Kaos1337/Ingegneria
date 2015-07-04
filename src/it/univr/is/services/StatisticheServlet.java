package it.univr.is.services;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet dedicata alle operazioni che coinvolgono statistiche
 * @author marco
 *
 */

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
		
		ArrayList<String> trenta_giorni_da_oggi = this.getLast_30Days();
		ArrayList<String> tutti_mesi = this.getAllMonthsFrom(2014,3);
		
		String[][] mesi = ds.getStatAssolute(tutti_mesi);
		
		//tolgo il giorno dalla data per indicare i valori nel mese
		String[] part;
		for(String[] s : mesi) {
			part = s[0].split("-");
			s[0] = part[0]+"-"+part[1];
		}
		
		request.setAttribute("dati_assoluti", mesi);
		request.setAttribute("dati_mensili", ds.getStatMensili(trenta_giorni_da_oggi));
		
		request.getRequestDispatcher("stats.jsp").forward(request, response);
	
		
	}
	
	/**
	 * Metodo per determinare gli ultimi 30 giorni
	 * @return ArrayList di stringhe ultimi 30 giorni
	 */
	private ArrayList<String> getLast_30Days(){
	    GregorianCalendar cal = new GregorianCalendar();
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	    ArrayList<String> dates = new ArrayList<String>();
	    
	    int day = cal.get(GregorianCalendar.DAY_OF_MONTH);
	    int month = cal.get(GregorianCalendar.MONTH);
	    int year = cal.get(GregorianCalendar.YEAR);

	    for(int i=day-29; i <= (day); i++){
	        	
	            cal.set(year, month, i);
	            dates.add(sdf.format(cal.getTime()));   
	        }
	    return dates;

	}
	
	/**
	 * Metodo per determinare i mesi passati
	 * @return ArrayList di stringhe dei mesi
	 */
	private ArrayList<String> getAllMonthsFrom(int anno, int mese){
		
		ArrayList<String> dates = new ArrayList<String>();
	
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        GregorianCalendar calendar = new GregorianCalendar(anno,mese-1,1);
        GregorianCalendar  actual = new GregorianCalendar();

        dates.add(sdf.format(calendar.getTime()));
        
        while (calendar.before(actual)) {
                
        	calendar.add(Calendar.MONTH, 1);
        	
            dates.add(sdf.format(calendar.getTime()));
                
                
        }
            
        return dates;
        
	}
	
}
