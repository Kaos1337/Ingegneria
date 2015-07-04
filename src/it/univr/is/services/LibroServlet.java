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
 * Servlet dedicata alle operazioni che coinvolgono dati libro
 * @author marco
 *
 */

public class LibroServlet extends AbstractServlet {
	private static final long serialVersionUID = 1L;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LibroServlet() {
        super();
    }


    /**
     * Metodo per determinare l'operazione da eseguire relativa ai libri
     */
	@Override
	void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String tipoInterrogazione = request.getParameter("mode");
		
		switch(tipoInterrogazione){
		
		case "libreria":
			this.getLibreria(request,response);
			break;
		
		case "inserimento_libro":
			this.insertLibro(request,response);
			break;
			
		case "elimina_libro":
		case "disponibile_libro":
		case "prenotato_libro":
		case "occupato_libro":
			this.aggiornaLibri(request,response);
			break;
			
		case "ricerca_libro":
			this.searchLibri(request,response);
			break;

			
		default :
			request.setAttribute("error", "Valore non gestito: "+ tipoInterrogazione);
			request.getRequestDispatcher("Errore.jsp").forward(request, response);
		
		}
	}


	/**
	 * Metodo per invio dati al Datasource relativi 
	 * alla libreria dell'utente loggato
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void getLibreria(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		request.setAttribute("lista_libri", ds.searchLibri((int) request.getSession().getAttribute("id")));
		
		request.getRequestDispatcher("manage.jsp").forward(request, response);
		
	}


	/**
	 * Invia dati al Datasource per ottenere la lista di libri 
	 * associata al filtro della request
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void searchLibri(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		bean = EntityFactory.getFactory(Constant.LIBRO).makeElement(request);
		String citta=request.getParameter("citta");
		String provincia= request.getParameter("provincia");
		String nome=request.getParameter("nome").trim();
		
	
		request.setAttribute("lista_libri", ds.searchLibri(bean, nome, citta, provincia));
		request.getRequestDispatcher("searchbook.jsp").forward(request, response);	
	}



	/**
	 * Metodo per invio dati al Datasource relativi all'operazione
	 * da effettuare per i titoli dell'utente
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void aggiornaLibri(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		String[] select = request.getParameterValues("selezione");
		int op=-1;
	
		
		switch(request.getParameter("mode")){
			case "disponibile_libro":op=0;break;
			case "prenotato_libro":op=1;break;
			case "occupato_libro":op=2;break;
			case "elimina_libro":op=3;break;
			default:
		}
		
		//se è stato selezionato almeno un libro
		if (select != null && select.length > 0 && op!=-1) {
			
			ds.updateLibri(select,op);
			request.removeAttribute("select");
		}
		
		request.setAttribute("lista_libri", ds.searchLibri( (int) request.getSession().getAttribute("id")));
		request.getRequestDispatcher("manage.jsp").forward(request, response);
		}


	/**
	 * Invia dati al Datasource per inserire un nuovo libro e
	 * indirizza alla libreria dell'utente fornendo la lista dei
	 * suoi libri
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void insertLibro(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		boolean reqValid=true;
		String error = "Dati non validi:<ul>";
		
		String param = request.getParameter("titolo").trim();
		if(param.equals("") || param.length()>40) {
			reqValid=false;
			error += "<li>titolo</li>";}
			
		param = request.getParameter("autore").trim();
		if(param.equals("") || param.length()<2 || param.length()>20) {
			reqValid=false;
			error += "<li>autore</li>";
		}
				
		param = request.getParameter("categoria");
		if(!(param.equalsIgnoreCase("Scienza") || param.equalsIgnoreCase("Fantasy") 
				|| param.equalsIgnoreCase("Filosofia") || param.equalsIgnoreCase("Giallo") 
				|| param.equalsIgnoreCase("Romanzo") ) ) {
			reqValid=false;
			error += "<li>prima categoria</li>";
		}
		
		param = request.getParameter("categoria2");
		if(!(param.equalsIgnoreCase("Scienza") || param.equalsIgnoreCase("Fantasy") 
				|| param.equalsIgnoreCase("Filosofia") || param.equalsIgnoreCase("Giallo") 
				|| param.equalsIgnoreCase("Romanzo") || param.equals("") ) ) {
			reqValid=false;
			error += "<li>seconda categoria</li>";
		}
				
		param = request.getParameter("edizione").trim();
		if(param.length()>10) {
			reqValid=false;
			error += "<li>edizione</li>";
		}
				
		param = request.getParameter("isbn").trim();
		try{
		if(param.length()!=13 || param.charAt(0)<48 || param.charAt(0)>57 || Long.parseLong(param)<=0) {
			reqValid=false;
			error += "<li>ISBN</li>";
		}
		}catch(NumberFormatException e){
	        reqValid=false;
			error += "<li>ISBN</li>";
	    }
				
		error+="</ul>";
		
		
		
		if (reqValid) {
			ds.insertLibro(EntityFactory.getFactory("LIBRO").makeElement(request), EntityFactory.getFactory("UTENTE").makeElement(request.getSession()));
			
			request.setAttribute("lista_libri", ds.searchLibri((int) request.getSession().getAttribute("id")));
			request.getRequestDispatcher("manage.jsp").forward(request, response);
		}
		
		else{
			request.setAttribute("error", error);
			request.getRequestDispatcher("insertbook.jsp").forward(request, response);
		
		}
	}
		
	

}
