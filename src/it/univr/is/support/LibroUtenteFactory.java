package it.univr.is.support;

import it.univr.is.entity.Entity;
import it.univr.is.entity.LibroUtente;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Factory di entità LibroUtente
 * @author marco
 *
 */
public class LibroUtenteFactory extends EntityFactory {

	/**
	 * crea un'istanza di LibroUtente
	 */
	@Override
	public Entity makeElement(HttpServletRequest request) {
		return new LibroUtente(request);
	}

	@Override
	public Entity makeElement(HttpSession session) {
		// TODO Auto-generated method stub
		return null;
	}

}
