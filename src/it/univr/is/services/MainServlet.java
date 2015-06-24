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
 * valori supportati: {"Iscrizione","Login","Recupero_psw",
 * "Recupero_newpsw","Modifica_utente","Inserimento_libro",
 * "Cancella_libro","Aggiorna_libro","Ricerca_libro",
 * "Moderatore","Statistiche"}
 * @author marco
 *
 */
@WebServlet("/MainServlet")
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
		
		// @FRANCESCO: 
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
						request.setAttribute("error", "Mode con valore non gestito: "+ tipoInterrogazione);
						request.getRequestDispatcher("Errore.jsp").forward(request, response);
						
					}
					
				} 
				
				// se Mode non settata
				else {
					//Nota: soluzione temporanea manda a pagina di errore
					request.setAttribute("error", "Mode senza valore: "+ tipoInterrogazione);
					request.getRequestDispatcher("Errore.jsp").forward(request, response);
					
					}
				}


}
