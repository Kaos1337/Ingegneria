package it.univr.is.services;

import it.univr.is.database.Datasource;
import it.univr.is.entity.Entity;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AbstractServlet
 * @author marco
 *
 */
@WebServlet("/AbstractServlet")
abstract public class AbstractServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected Datasource ds = new Datasource();
	protected Entity bean ;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AbstractServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}
	
	
	/**
	 * Metodo da implementare nei figli per eseguire operazioni relative alla request
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	abstract void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
	
}
