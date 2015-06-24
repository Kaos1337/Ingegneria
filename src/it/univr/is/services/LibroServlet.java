package it.univr.is.services;

import it.univr.is.entity.Libro;
import it.univr.is.entity.Utente;
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
		
		String tipoInterrogazione = request.getParameter("Mode");
		
		switch(tipoInterrogazione){
		
		case "Inserimento_libro":
			this.insertLibro(request,response);
			break;
			
		case "Cancella_libro":
			this.cancellaLibri(request,response);
			break;

			
		default :
			request.setAttribute("error", "Valore non gestito: "+ tipoInterrogazione);
			request.getRequestDispatcher("Errore.jsp").forward(request, response);
		
		}
	}


	private void cancellaLibri(HttpServletRequest request,
			HttpServletResponse response) {
		
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
		
		libro =  (Libro) EntityFactory.getFactory(libro).makeElement(request);
		libro.setUtente(((Utente) request.getAttribute("utente")).getEmail());
		
		ds.insertLibro(libro);
		
		request.setAttribute("ListaLibri", ds.searchLibri(libro));
		request.getRequestDispatcher("Libreria.jsp").forward(request, response);
		
	}
		
	

}
