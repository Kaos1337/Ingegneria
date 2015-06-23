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
 * Servlet dedicata alle operazioni che coinvolgono dati utente
 * @author marco
 *
 */
@WebServlet("/UtenteServlet")
public class UtenteServlet extends AbstractServlet {
	private static final long serialVersionUID = 1L;
       
	private Utente utente = new Utente();
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UtenteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	@Override
	void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String tipoInterrogazione = request.getParameter("Mode");
		
		switch(tipoInterrogazione){
		
		case "Iscrizione":
			this.checkAndSubscribe(request,response);
			break;
			
		case "Login":
			//TODO
			
		case "Recupero_psw":
			this.checkMail(request, response);
			break;
			
		case "Recupero_newpsw":
			this.updatePsw(request, response);
			break;
		case "Modifica_utente":
			
		default :
			request.setAttribute("error", "Mode senza valore: "+ tipoInterrogazione);
			request.getRequestDispatcher("Errore.jsp").forward(request, response);
		
		}
	}

	/**
	 * Metodo per invio dati sostituzione psw al Datasource
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void updatePsw(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		utente =  (Utente) EntityFactory.getFactory(utente).makeElement(request);
		
		
		//se la sostituzione ha successo
		if(!ds.updatePswl(utente)){//! da eliminare all'implementazione del datasource
			request.setAttribute("msg", "Ora puoi effettuare il login");
			request.getRequestDispatcher("Login.jsp").forward(request, response);
			
		}
		
		//altrimenti riprova a cambiare password
		else{
		request.setAttribute("error", "Password non accettata, perfavore riprova");
		request.getRequestDispatcher("NuovaPsw.jsp").forward(request, response);
		}
		
	}

	/**
	 * Metodo per invio dati di verifica email al Datatsource
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void checkMail(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		//se c'è riscontro eseguo effettive operazioni
		if(!ds.checkMail(request.getParameter("email"))){//! da eliminare all'implementazione del datasource
			//TODO
		}
			
		//in ogni caso dico che la mail è stata inviata
		request.setAttribute("msg", "Controlla la tua casella di posta elettronica");
		request.getRequestDispatcher("Login.jsp").forward(request, response);
		
		
	}

	/**
	 * Metodo per invio dati di iscrizione Utente al Datasource
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void checkAndSubscribe(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		//faccio uso dell'abstract factory, sarebbe stato equivalente new Utente(request)
		//se vogliamo dirla tutta non è utile qui perchè comunque uso Utente, dovrei piuttosto 
		//usare solo riferimenti a Entity. Intanto l'ho messa, chiedetemi
		utente =  (Utente) EntityFactory.getFactory(utente).makeElement(request);
		
		//se l'inserimento ha successo
		if(!ds.checkAndSubscribe(utente)){//! da eliminare all'implementazione del datasource
			request.setAttribute("utente", utente);//da eliminare quando esempio non più necessario
			request.getRequestDispatcher("Pagina.jsp").forward(request, response);
		}
		
		//altrimenti 
		else {
			request.setAttribute("errore", "Impossibile completare l'iscrizione");
			request.getRequestDispatcher("Errore.jsp").forward(request, response);
		}
		
	}
	
	
}