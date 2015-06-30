package it.univr.is.services;

import it.univr.is.entity.Entity;
import it.univr.is.entity.Libro;
import it.univr.is.entity.LibroUtente;
import it.univr.is.support.Constant;
import it.univr.is.support.EntityFactory;

import java.io.IOException;
import java.util.ArrayList;

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
@WebServlet("/LibroServlet")
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
		
		///////////// TEMP
		Libro libro = new Libro();
		libro.setTitolo("V per vendetta");
		libro.setAutore("Darkaos");
		libro.setCategoria("Gore");
		libro.setCategoria2("Telegram");
		libro.setCopertina("}:-)");
		libro.setEdizione("1 Edizione");
		libro.setIsbn("1234567890AAA");
		libro.setStato(0);
		ArrayList<Libro> al= new ArrayList<Libro>();
		al.add(libro);
		libro=new Libro();
		libro.setStato(1);
		libro.setTitolo("Merda d'artista");
		libro.setCopertina("img/cop/cop_2.jpg");
		al.add(libro);
		libro=new Libro();
		libro.setStato(2);
		libro.setTitolo("Mulino stanco");
		al.add(libro);
		libro=new Libro();
		libro.setTitolo("It's all ogre");
		al.add(libro);
		libro=new Libro();
		libro.setTitolo("Gregorio e grattacapi");
		al.add(libro);
		
		request.setAttribute("lista_libri", al);
		//////////////TEMP
		
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
		
		String citta=request.getParameter("citta");
		String provincia= request.getParameter("provincia");
		String nominativo=request.getParameter("nome").trim();
		
		
		bean = (Libro) EntityFactory.getFactory(Constant.LIBRO).makeElement(request);
		
		ArrayList<Entity> al = new ArrayList<Entity>();
		
		int i;
		String substring="";
		while(!nominativo.isEmpty()) {
			i = nominativo.indexOf(" ");
			
			if(i>-1) 
				substring = nominativo.substring(0, i-1);
			
			al.addAll(ds.searchLibri(bean, substring, citta, provincia));
			
			nominativo=nominativo.substring(i);
		}
		
	///////////// TEMP
		LibroUtente libroutente = new LibroUtente();
		libroutente.setTitolo("V per vendetta");
		libroutente.setAutore("Darkaos");
		libroutente.setCategoria("Gore");
		libroutente.setCategoria2("Telegram");
		libroutente.setCopertina("}:-)");
		libroutente.setEdizione("1 Edizione");
		libroutente.setIsbn("1234567890AAA");
		libroutente.setCitta("Garda");
		libroutente.setProvincia("Verona");
		libroutente.setId(1211);
		
		al.add(libroutente);
		libroutente=new LibroUtente();
		libroutente.setTitolo("Merda d'artista");
		libroutente.setId(1212);
		libroutente.setCopertina("img/cop/cop_2.jpg");
		al.add(libroutente);
		libroutente=new LibroUtente();
		libroutente.setTitolo("Mulino stanco");
		libroutente.setId(1213);
		al.add(libroutente);
		libroutente=new LibroUtente();
		libroutente.setTitolo("It's all ogre");
		libroutente.setId(1214);
		al.add(libroutente);
		libroutente=new LibroUtente();
		libroutente.setTitolo("Gregorio e grattacapi");
		libroutente.setId(1215);
		al.add(libroutente);
		
	///////// TEMP
		request.setAttribute("lista_libri", al);
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
		
		String select[] = request.getParameterValues("selezione");
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
		String error = "Dati non validi:/n<ul>";
		
		String param = request.getParameter("titolo");
		if(param==null || param.length()>40) {
			reqValid=false;
			error += "<li>titolo</li>";}
			
		param = request.getParameter("autore");
		if(param==null || param.length()<2 || param.length()>20) {
			reqValid=false;
			error += "<li>autore</li>";
		}
				
		param = request.getParameter("categoria");
		if(param==null ) {
			reqValid=false;
			error += "<li>categoria</li>";
		}
				
		param = request.getParameter("edizione");
		if(param!=null &&  (param.length()>10)) {
			reqValid=false;
			error += "<li>edizione</li>";
		}
				
		param = request.getParameter("isbn");
		if(param!=null && param.length()!=13) {
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
