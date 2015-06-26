package it.univr.is.services;

import it.univr.is.entity.Libro;
import it.univr.is.entity.Utente;
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
       
	Libro libro = new Libro();
	
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
			this.searchLibreria(request,response);
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
	private void searchLibreria(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		///////////// TEMP
		//request.setAttribute("lista_libri", ds.searchLibri(((Utente) request.getSession().getAttribute("utente")).getEmail()));
		libro.setTitolo("V per vendetta");
		libro.setAutore("Darkaos");
		libro.setCategoria("Gore");
		libro.setCategoria2("Telegram");
		libro.setCopertina("}:-)");
		libro.setEdizione("1 Edizione");
		libro.setIsbn("1234567890AAA");
		ArrayList<Libro> al= new ArrayList<Libro>();
		al.add(libro);
		libro.setTitolo("Merda d'artista");
		al.add(libro);
		libro.setTitolo("Mulino stanco");
		al.add(libro);
		libro.setTitolo("It's all ogre");
		al.add(libro);
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
		
		libro = (Libro) EntityFactory.getFactory("LIBRO").makeElement(request);
		
		request.setAttribute("lista_libri", ds.searchLibri(libro, request.getParameter("citta"),request.getParameter("provincia")));
		request.getRequestDispatcher("Ricerca.jsp").forward(request, response);
		
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
		String utente = ((Utente)request.getSession().getAttribute("utente")).getEmail();
		
		switch(request.getParameter("mode")){
			case "disponibile_libro":op=0;break;
			case "prenotato_libro":op=1;break;
			case "occupato_libro":op=2;break;
			case "elimina_libro":op=3;break;
			default:
		}
		
		//se è stato selezionato almeno un libro
		if (select != null && select.length > 0) {
			
			ds.updateLibri(select,utente,op);
			request.removeAttribute("select");
		}
		
		request.setAttribute("lista_libri", ds.searchLibri(utente));
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
		
		libro =  (Libro) EntityFactory.getFactory("LIBRO").makeElement(request);
		libro.setUtente(((Utente) request.getAttribute("utente")).getEmail());
		
		ds.insertLibro(libro);
		
		request.setAttribute("lista_libri", ds.searchLibri(libro));
		request.getRequestDispatcher("manage.jsp").forward(request, response);
		
	}
		
	

}
