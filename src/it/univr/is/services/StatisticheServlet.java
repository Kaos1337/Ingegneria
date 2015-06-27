package it.univr.is.services;

import it.univr.is.entity.Utente;
import it.univr.is.support.EntityFactory;

import java.io.IOException;

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
       
	private Utente utente = new Utente();
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StatisticheServlet() {
        super();
        // TODO Auto-generated constructor stub
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
			this.getData();
			break;
		default :
			request.setAttribute("error", "Valore non gestito: "+ tipointerrogazione);
			request.getRequestDispatcher("error.jsp").forward(request, response);
		
		}
	}

	private void getData() {
		// TODO Auto-generated method stub
		
	}
	
	
	
	
}
