package it.univr.is.services;

import it.univr.is.entity.Utente;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class UtenteServlet
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
		
		switch(request.getParameter("Mode")){
		case "Iscrizione":
			this.checkAndSubscribe(request,response);
			break;
		case "Login":
		case "Recupero_psw":
		case "Recupero_newpsw":
		case "Modifica_utente":
		
	}

	;
}

	private void checkAndSubscribe(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		
		utente.setNome(request.getParameter("nome"));
		utente.setCognome(request.getParameter("cognome"));
		utente.setEmail(request.getParameter("email") );
		utente.setPassword(request.getParameter("password"));
		utente.setVia(request.getParameter("via"));
		utente.setCap(request.getParameter("cap"));
		utente.setCitta(request.getParameter("citta"));
		utente.setProvincia(request.getParameter("provincia"));
		
		//se l'inserimento ha successo
		if(ds.checkAndSubscribe(utente));
			//request.getRequestDispatcher("Pagina.jsp").forward(request, response);
		
		//altrimenti 
		else {
		request.setAttribute("utente", utente);
		request.getRequestDispatcher("Pagina.jsp").forward(request, response);
		}
		
	}
}
