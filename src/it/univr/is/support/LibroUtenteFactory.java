package it.univr.is.support;

import it.univr.is.entity.Entity;
import it.univr.is.entity.Libro;

import javax.servlet.http.HttpServletRequest;

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
		return new Libro(request);
	}

}
