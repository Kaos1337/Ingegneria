package it.univr.is.services;

import it.univr.is.database.Datasource;
import it.univr.is.entity.Utente;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet per comunicazione tra client e classe Datasource
 * che recupera i dati dal DB
 */

public class MainServlet extends AbstractServlet {
	private static final long serialVersionUID = 1L;
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MainServlet() {
        super();
    }

	/*
	 * Effettua le operazioni relative al tipo di request (sia GET che POST)
	 * 
	 * @param request
	 * @param response
	 */
	
	public void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// FRANCESCO: 
		// -nella form in action indichi il servlet e come metodo GET o POST
		// -nel tuo pulsante submit delle form dovresti scrivere qualcosa del tipo 
		// <input type="submit" name="Mode" value="Iscrizione"> cosicchè io catturando
		// qui il Mode sappia che la request contiene ad esempio dati per l'iscrizione utente 
		
		// ottenimento tipo di richiesta
				String tipoInterrogazione = request.getParameter("Mode");
				
				if (tipoInterrogazione != null) {
					
					switch(tipoInterrogazione){
					case "Iscrizione":
					case "Login":
					case "Recupero_psw":
					case "Recupero_newpsw":
					case "Modifica_utente":
						request.getRequestDispatcher("UtenteServlet").forward(request, response);
						break;
					case "Inserimento_libro":
					case "Cancella_libro":
					case "Aggiorna_libro":
					case "Ricerca_libro":
						request.getRequestDispatcher("LibroServlet").forward(request, response);
						break;
					case "Moderatore":
						request.getRequestDispatcher("ModeratoreServlet").forward(request, response);
						break;
					case "Statistiche":
						request.getRequestDispatcher("StatisticheServlet").forward(request, response);
						break;
					default:
						System.out.println("Errore");
					}
					
				} 
				else {
					//caso per errore (metodo non indicato) torna home o pagina errore?
					//request.getRequestDispatcher("Home.jsp").forward(request, response);
					//TODO
					}
				}


}
