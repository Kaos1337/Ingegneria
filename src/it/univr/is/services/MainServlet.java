package it.univr.is.services;

import it.univr.is.database.Datasource;

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

public class MainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private Datasource ds = new Datasource();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MainServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	/*
	 * Effettua le operazioni relative al tipo di request (sia GET che POST)
	 * 
	 * @param request
	 * @param response
	 */
	
	private void processRequest(HttpServletRequest request, HttpServletResponse response) {
		
		// FRANCESCO: 
		// -nella form in action indichi il servlet e come metodo GET o POST
		// -nel tuo pulsante submit delle form dovresti scrivere qualcosa del tipo 
		// <input type="submit" name="Mode" value="Iscrizione"> cosicchè io catturando
		// qui il Mode sappia che la request contiene ad esempio dati per l'iscrizione utente 
		
		// ottenimento tipo di richiesta
				String tipoInterrogazione = request.getParameter("Mode");
				
				if (tipoInterrogazione != null) {
					
					//Caso Form di iscrizione
					if (tipoInterrogazione.equalsIgnoreCase("Iscrizione")) {
						//request.setAttribute("localMode", "INSERT");
						//TODO
						//request.getRequestDispatcher("Pagina.jsp").forward(request, response);
        			
					}
					
					//Caso Form di Login
					if (tipoInterrogazione.equalsIgnoreCase("Login")) {
										
					}
					
					//Caso Form di recupero password
					if (tipoInterrogazione.equalsIgnoreCase("Recupero_psw")) {
						//request.setAttribute("localMode", "DELETE");
					}
					
					//Caso Form di inserimento nuova password
					if (tipoInterrogazione.equalsIgnoreCase("Recupero_newpsw")) {
										
					}
					
					//Caso Form di modifica dati utente
					if (tipoInterrogazione.equalsIgnoreCase("Modifica")) {
										
					}
					
					//Caso Form di inserimento nuovo libro
					if (tipoInterrogazione.equalsIgnoreCase("Inserimento_libro")) {
										
					}
					
					//Caso Form di eliminazione libro
					if (tipoInterrogazione.equalsIgnoreCase("Cancella_libro")) {
										
					}
					
					//Caso Form di Login
					if (tipoInterrogazione.equalsIgnoreCase("Login")) {
										
					}
					
					//Caso Form di aggiornamento stato libro
					if (tipoInterrogazione.equalsIgnoreCase("Aggiorna_libro")) {
										
					}
			       
					//Caso Form di Login
					if (tipoInterrogazione.equalsIgnoreCase("Login")) {
										
					}
					
					//Caso Form di Ricerca libri con filtri
					if (tipoInterrogazione.equalsIgnoreCase("Ricerca")) {
										
					}
					
					//Caso Form modifica del moderatore da Segnalazione 
					if (tipoInterrogazione.equalsIgnoreCase("Moderatore")) {
										
					}
					
					//Caso Form Ottenimento statistiche
					if (tipoInterrogazione.equalsIgnoreCase("Statistiche")) {
										
					}
					
				} 
				else {
					//caso per errore (metodo non indicato) torna home o pagina errore?
					//request.getRequestDispatcher("Home.jsp").forward(request, response);
					//TODO
					}
				}


}
