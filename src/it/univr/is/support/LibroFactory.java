package it.univr.is.support;

import it.univr.is.entity.Entity;
import it.univr.is.entity.Libro;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Factory di entità Libro
 * @author marco
 *
 */
public class LibroFactory extends EntityFactory {

	/**
	 * crea un'istanza di Libro
	 */
	@Override
	public Entity makeElement(HttpServletRequest request) {
		return new Libro(request);
	}

	@Override
	public Entity makeElement(HttpSession session) {
		// TODO Auto-generated method stub
		return null;
	}

}
