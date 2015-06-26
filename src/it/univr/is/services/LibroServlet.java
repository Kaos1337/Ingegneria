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
			
		case "cancella_libro":
			this.cancellaLibri(request,response);
			break;
			
		case "aggiorna_libro":
			//TODO
		case "ricerca_libro":
			this.searchLibri(request,response);
			break;

			
		default :
			request.setAttribute("error", "Valore non gestito: "+ tipoInterrogazione);
			request.getRequestDispatcher("Errore.jsp").forward(request, response);
		
		}
	}


	/**
	 * Metodo per invio dati al Datasource relativi all'utente loggato
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void searchLibreria(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		request.setAttribute("lista_libri", ds.searchLibri(((Utente) request.getSession().getAttribute("utente")).getEmail()));
		
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


	
	private void cancellaLibri(HttpServletRequest request,
			HttpServletResponse response) {
		
		int size = Integer.parseInt(request.getParameter("size"));
		ArrayList<String> listatitoli = new ArrayList<String>();
		for (int i=1 ; i<=size ; i++ ) {
			if(Boolean.getBoolean((request.getParameter("selected"+i))));
				listatitoli.add(request.getParameter("titolo"+i));
		}
		
		
		//TODO passare una lista da jsp a servlet si può fare in due modi:
		//
		//- Soluzione che ho pensato: racchiudo la lista nella session 
		//	(quindi è a tutti gli effetti un oggetto)
		//	e poi nella servlet la rimuovo dalla sessione
		//
		//- Soluzione usata nell'esempio dei vini: nella request raccolgo 
		//	dati indirettamente sulla lista, creando un numero
		//  dinamico di parametri attraverso un ciclo e tenendone la grandezza;
		//  
		
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
		
		request.setAttribute("ListaLibri", ds.searchLibri(libro));
		request.getRequestDispatcher("Libreria.jsp").forward(request, response);
		
	}
		
	

}
