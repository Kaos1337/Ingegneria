package it.univr.is.services;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet di interfaccia con le pagine.
 * Determina le operazioni da eseguire in base al 
 * valore String del parametro "Mode" nella request;
 * valori supportati: {"iscrizione","login","logout","recupero_psw",
 * "recupero_newpsw","modifica_utente","contatta_utente",
 * "manda_messaggio","libreria","inserimento_libro",
 * "cancella_libro","aggiorna_libro","ricerca_libro",
 * "moderatore","statistiche"}
 * @author marco
 *
 */

public class MainServlet extends AbstractServlet {
	private static final long serialVersionUID = 1L;
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MainServlet() {
        super();
    }

	
	/**
	 * Determina a quale Servlet secondaria deve essere passata la richiesta
	 */
	public void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// ottenimento tipo di richiesta
				String tipoInterrogazione = request.getParameter("mode");
				
				if (tipoInterrogazione != null) {
					
					switch(tipoInterrogazione){
					case "iscrizione":
					case "login":
					case "logout":
					case "recupero_psw":
					case "recupero_newpsw":
					case "modifica_utente":
					case "contatta_utente":
					case "manda_messaggio":
						request.getRequestDispatcher("UtenteServlet").forward(request, response);
						break;
					case "libreria":
					case "inserimento_libro":
					case "cancella_libro":
					case "aggiorna_libro":
					case "ricerca_libro":
						request.getRequestDispatcher("LibroServlet").forward(request, response);
						break;
					case "moderatore":
						request.getRequestDispatcher("ModeratoreServlet").forward(request, response);
						break;
					case "statistiche":
						request.getRequestDispatcher("StatisticheServlet").forward(request, response);
						break;
					default:
						request.setAttribute("error", "Mode con valore non gestito: "+ tipoInterrogazione);
						request.getRequestDispatcher("error.jsp").forward(request, response);
						
					}
					
				} 
				
				// se Mode non settata
				else {
					//Nota: soluzione temporanea manda a pagina di errore
					request.setAttribute("error", "Mode senza valore: "+ tipoInterrogazione);
					request.getRequestDispatcher("error.jsp").forward(request, response);
					
					}
				}


}
