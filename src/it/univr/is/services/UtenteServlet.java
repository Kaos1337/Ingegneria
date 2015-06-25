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

    /**
     * Metodo per determinare l'operazione da eseguire relativa ai dati utente
     */
	@Override
	void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String tipoInterrogazione = request.getParameter("mode");
		
		switch(tipoInterrogazione){
		
		case "iscrizione":
			this.checkAndSubscribe(request,response);
			break;
			
		case "login":
			this.login(request, response);
			break;
			
		case "logout":
			request.getSession().invalidate();
			response.sendRedirect("index.jsp");
			break;
			
		case "recupero_psw":
			this.checkMail(request, response);
			break;
			
		case "recupero_newpsw":
			this.updatePsw(request, response);
			break;
			
		case "modifica_utente":
			this.updateUtente(request,response);
			break;
			
		default :
			request.setAttribute("error", "Valore non gestito: "+ tipoInterrogazione);
			request.getRequestDispatcher("error.jsp").forward(request, response);
		
		}
	}

	/**
	 * Metodo per invio dati al Datasource relativi alle 
	 * credenziali di login utente
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws ServletException
	 */
	private void login(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		
		utente =  (Utente) EntityFactory.getFactory("UTENTE").makeElement(request);
		
		//verifico validità credenziali
		utente = ds.login(utente);
		
		//se c'è riscontro delle credenziali
		if(utente!=null){
			
			utente.setPassword(null);
			request.getSession().setAttribute("utente",utente );
			response.sendRedirect("index.jsp");
				
			}
				
		//altrimenti ricarica la pagina per immettere nuovi dati
		else{
			request.setAttribute("error", "Dati o password non accettabili");
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}
		
	}

	/**
	 * Metodo per invio dati al Datasource relativi all'aggiornameneto
	 * utente
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void updateUtente(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		//controllo validità dati
		boolean reqValid = true;
		String error = "Dati non validi:/n<ul>";
			
		String param = request.getParameter("email");
		if(param==null || !param.contains("@") || param.length()>25) {
			reqValid=false;
			error += "<li>email</li>";}
			
		param = request.getParameter("password");
		if(param==null || param.length()<4 || param.length()>25) {
			reqValid=false;
			error += "<li>password</li>";
		}
				
		param = request.getParameter("via");
		if(param==null || param.length()>25) {
			reqValid=false;
			error += "<li>via</li>";
		}
				
		param = request.getParameter("civico");
		if(param==null || (new Integer(param)) instanceof Integer || param.length()>25) {
			reqValid=false;
			error += "<li>civico</li>";
		}
				
		param = request.getParameter("cap");
		if(param==null || param.length()>25) {
			reqValid=false;
			error += "<li>cap</li>";
		}
				
		param = request.getParameter("citta");
		if(param==null || param.length()>25) {
			reqValid=false;
			error += "<li>citta</li>";
		}
				
		param = request.getParameter("provincia");
		if(param==null || param.length()>25) {
			reqValid=false;
			error += "<li>provincia</li>";
		}
	
		//raccolgo i dati nuovi dalla request
		utente =  (Utente) EntityFactory.getFactory("UTENTE").makeElement(request);
		
		//raccolgo dati attuali dalla sessione (e psw da campo immesso nella request)
		//Nota: chi fa controllo su corrispondenza con psw attuale? Qui ho considerato servlet,
		//se è conservata nella sessione può essere fatta su client?
		Utente utente_attuale = (Utente) request.getSession().getAttribute("utente");
		utente_attuale.setPassword((String) request.getAttribute("password_attuale"));
		
		//se l'aggiornamento ha successo
		if(!ds.updateUtente(utente, utente_attuale) && reqValid){//! da eliminare all'implementazione del datasource
			request.getRequestDispatcher("index.jsp").forward(request, response);
			
		}
		
		//altrimenti ricarica la pagina per immettere nuovi dati
		else{
		request.setAttribute("error", error+"</ul>");
		request.getRequestDispatcher("ucp.jsp").forward(request, response);
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
		
		boolean reqValid = true;
		
		String param = request.getParameter("password");
		if(param==null || param.length()<4 || param.length()>25) reqValid=false;
		
		
		utente =  (Utente) EntityFactory.getFactory("UTENTE").makeElement(request);
		
		//se la sostituzione ha successo
		if(!ds.updatePswl(utente) && reqValid){//! da eliminare all'implementazione del datasource
			request.setAttribute("info", "Ora puoi effettuare il login");
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
			//TODO invio mail
		}
			
		//in ogni caso dico che la mail è stata inviata
		request.setAttribute("info", "Controlla la tua casella di posta elettronica");
		request.getRequestDispatcher("login.jsp").forward(request, response);
		
		
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
		
		//controllo validità dati
		boolean reqValid = true;
		String error = "Dati non validi:/n<ul>";
		
		String param = request.getParameter("nome");
		if(param==null || param.length()<2 || param.length()>25) {
			reqValid=false;
			error += "<li>nome</li>";
		}
		
		param = request.getParameter("cognome");
		if(param==null || param.length()<4 || param.length()>25) {
			reqValid=false;
			error += "<li>cognome</li>";
		}
		
		param = request.getParameter("email");
		if(param==null || !param.contains("@") || param.length()>25) {
			reqValid=false;
			error += "<li>email</li>";}
			
		param = request.getParameter("password");
		if(param==null || param.length()<4 || param.length()>25) {
			reqValid=false;
			error += "<li>password</li>";
		}
				
		param = request.getParameter("via");
		if(param==null || param.length()>25) {
			reqValid=false;
			error += "<li>via</li>";
		}
				
		param = request.getParameter("civico");
		if(param==null || (new Integer(param)) instanceof Integer || param.length()>25) {
			reqValid=false;
			error += "<li>civico</li>";
		}
				
		param = request.getParameter("cap");
		if(param==null || param.length()>25) {
			reqValid=false;
			error += "<li>cap</li>";
		}
				
		param = request.getParameter("citta");
		if(param==null || param.length()>25) {
			reqValid=false;
			error += "<li>citta</li>";
		}
				
		param = request.getParameter("provincia");
		if(param==null || param.length()>25) {
			reqValid=false;
			error += "<li>provincia</li>";
		}
		
		utente =  (Utente) EntityFactory.getFactory("UTENTE").makeElement(request);
		
		//se l'inserimento ha successo
		if(!ds.checkAndSubscribe(utente) && reqValid){//! da eliminare all'implementazione del datasource
			request.getSession().setAttribute("utente", utente);
			response.sendRedirect("index.jsp");
		
		}
		
		//altrimenti 
		else {
			request.setAttribute("error", "Impossibile completare l'iscrizione");
			request.getRequestDispatcher("register.jsp").forward(request, response);
		}
		
	}
	
	
}
