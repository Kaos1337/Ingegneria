package it.univr.is.services;

import it.univr.is.support.Constant;
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
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UtenteServlet() {
        super();
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
			
		case "contatta_utente":
			this.contattaUtente(request,response);
			break;
			
		case "manda_messaggio":
			this.inviaMsg(request,response);
			break;
			
		case "contattaci":
			this.contact(request,response);
			break;
			
		default :
			request.setAttribute("error", "Valore non gestito: "+ tipoInterrogazione);
			request.getRequestDispatcher("error.jsp").forward(request, response);
		
		}
	}
	
	/**
	 * Manda un messaggio traendo i dati necessari dalla request
	 * @param request
	 */
	private void mandaMsg(HttpServletRequest request) {
		// TODO Implementazione invio messaggio
		
	}
	
	/**
	 * Metodo per controllo campi e risposta all'invio di messaggi
	 * con destinatari il dipartimento
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void contact(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		boolean reqValid = true;
		String error = "Perfavore completa i campi:<ul>";
		
		String param = request.getParameter("messaggio");
		if(param==null) {
			reqValid=false;
			error += "<li>messaggio</li>";}
			
		param = request.getParameter("oggetto");
		if(param==null) {
			reqValid=false;
			error += "<li>oggetto</li>";
		}
		
		param = request.getParameter("nomecognome");
		if(param==null) {
			reqValid=false;
			error += "<li>nome e cognome</li>";}
		
		param = request.getParameter("email");
		if(param==null) {
			reqValid=false;
			error += "<li>email</li>";}
		
		error+="</ul>";
		
		if (reqValid){
			this.mandaMsg(request);//param "reparto" contiene dipartimento indicato
			response.sendRedirect("contactus.jsp?info=La richiesta è stata inviata, attendi la risposta.");
		}
		
		else {
			request.setAttribute("error", error);
			request.getRequestDispatcher("contactus.jsp").forward(request, response);
			
		}
		
	}

	/**
	 * Metodo per controllo campi e risposta all'invio di messaggi
	 * con destinatari altri utenti
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void inviaMsg(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		boolean reqValid = true;
		String error = "Perfavore completa i campi:<ul>";
		
		String param = request.getParameter("messaggio");
		if(param==null) {
			reqValid=false;
			error += "<li>messaggio</li>";}
			
		param = request.getParameter("oggetto");
		if(param==null) {
			reqValid=false;
			error += "<li>oggetto</li>";
		}
		
		error+="</ul>";
		
		if (reqValid){
			this.mandaMsg(request);
			response.sendRedirect("reservebook.jsp?info=La richiesta è stata inviata, attendi la risposta.");
		}
		
		else {
			request.setAttribute("error", error);
			request.getRequestDispatcher("reservebook.jsp").forward(request, response);
			
		}	
		
	}

	/**
	 * Metodo per recupero dati dell'utente da contattare
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void contattaUtente(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		request.setAttribute("proprietario", ds.getUtente(Integer.parseInt(request.getParameter("proprietario"))));
		request.getRequestDispatcher("reservebook.jsp").forward(request, response);
		
		
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
		
		//verifico validità credenziali
		bean = ds.login(EntityFactory.getFactory(Constant.UTENTE).makeElement(request));
		
		
		//se c'è riscontro delle credenziali
		if(bean!=null){
			
			request.getSession().setAttribute("utente", bean );
			request.getSession().setAttribute("id", bean.getId() );
			
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
		String error = "Dati non validi:<ul>";
			
		String param = request.getParameter("email");
		if(param!=null && (!param.contains("@") || param.length()>40)) {
			reqValid=false;
			error += "<li>email</li>";}
			
		param = request.getParameter("password");
		if(param!=null && (param.length()<4 || param.length()>20)) {
			reqValid=false;
			error += "<li>password</li>";
		}
		
		param=request.getParameter("password_attuale");		
		if(param==null || param.length()<4 || param.length()>20) {
			reqValid=false;
			error += "<li>password attuale</li>";
		}
		
		param = request.getParameter("via");
		if(param!=null && param.length()>20) {
			reqValid=false;
			error += "<li>via</li>";
		}
				
		param = request.getParameter("civico");
		if(param!=null && param.length()>3) {
			reqValid=false;
			error += "<li>civico</li>";
		}
		else
			for (char c : param.toCharArray())
				if ( (int) c < 48 || (int) c > 57){
					reqValid=false;
					error += "<li>civico</li>";
				}
				
		param = request.getParameter("cap");
		if(param!=null && param.length()!=5) {
			reqValid=false;
			error += "<li>cap</li>";
		}
				
		param = request.getParameter("citta");
		if(param!=null && param.length()>20) {
			reqValid=false;
			error += "<li>citta</li>";
		}
				
		param = request.getParameter("provincia");
		if(param!=null && param.length()!=2) {
			reqValid=false;
			error += "<li>provincia</li>";
		}
		
		error+="</ul>";
		
		if(reqValid){
		//raccolgo i dati nuovi dalla request
		bean = EntityFactory.getFactory(Constant.UTENTE).makeElement(request);
		
			//se l'aggiornamento ha successo
			if(ds.updateUtente(bean, EntityFactory.getFactory(Constant.UTENTE).makeElement(request.getSession()), request.getParameter("password_attuale"))){
				request.getSession().invalidate();
				request.getRequestDispatcher("index.jsp").forward(request, response);
				
			}
			
			//altrimenti ricarica la pagina per immettere nuovi dati
			else{
			request.setAttribute("error","Perfavore controlla di aver inserito correttamente la tua password attuale");
			request.getRequestDispatcher("ucp.jsp").forward(request, response);
			}
		}
		else
			request.setAttribute("error",error);
			request.getRequestDispatcher("ucp.jsp").forward(request, response);
		
		
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
		if(param==null || param.length()<4 || param.length()>20) reqValid=false;
		
		
		//se password accettata
		if(reqValid){
			ds.updatePswl(EntityFactory.getFactory(Constant.UTENTE).makeElement(request));
			request.setAttribute("info", "Ora puoi effettuare il login");
			request.getRequestDispatcher("login.jsp").forward(request, response);
			
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
		if(ds.checkMail(request.getParameter("email"))){
			this.mandaMsg(request);
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
		String error = "Dati non validi:<ul>";
		
		String param = request.getParameter("nome");
		if(param==null || param.length()<2 || param.length()>20) {
			reqValid=false;
			error += "<li>nome</li>";
		}
		
		param = request.getParameter("cognome");
		if(param==null || param.length()<4 || param.length()>20) {
			reqValid=false;
			error += "<li>cognome</li>";
		}
		
		param = request.getParameter("email");
		if(param==null || !param.contains("@") || param.length()>40) {
			reqValid=false;
			error += "<li>email</li>";}
			
		param = request.getParameter("password");
		if(param==null || param.length()<4 || param.length()>20) {
			reqValid=false;
			error += "<li>password</li>";
		}
				
		param = request.getParameter("via");
		if(param==null || param.length()>20) {
			reqValid=false;
			error += "<li>via</li>";
		}
				
		param = request.getParameter("civico");
		if(param==null || param.length()>3) {
			reqValid=false;
			error += "<li>civico</li>";
		}
		else
			for (char c : param.toCharArray())
				if ( (int) c < 48 || (int) c > 57){
					reqValid=false;
					error += "<li>civico</li>";
				}
				
		param = request.getParameter("cap");
		if(param==null || param.length()!=5) {
			reqValid=false;
			error += "<li>cap</li>";
		}
				
		param = request.getParameter("citta");
		if(param==null || param.length()>20) {
			reqValid=false;
			error += "<li>citta</li>";
		}
				
		param = request.getParameter("provincia");
		if(param==null || param.length()!=2) {
			reqValid=false;
			error += "<li>provincia</li>";
		}
		
		error+="</ul>";
		
		if(reqValid){
		bean = EntityFactory.getFactory(Constant.UTENTE).makeElement(request);

			//se l'inserimento ha successo
			if(ds.checkAndSubscribe(bean)){
				response.sendRedirect("index.jsp");
			
			}
			
			//altrimenti 
			else {
				request.setAttribute("error", "Mail già in uso, è necessario usare una mail differente.");
				request.getRequestDispatcher("register.jsp").forward(request, response);
			}
		}
		else {
			request.setAttribute("error", error);
			request.getRequestDispatcher("register.jsp").forward(request, response);
		}
			
		
		
		
	}
	
	
}
