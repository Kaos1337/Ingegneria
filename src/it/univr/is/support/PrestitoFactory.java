package it.univr.is.support;

import it.univr.is.entity.Entity;
import it.univr.is.entity.Prestito;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Factory di entità Prestito
 * @author marco
 *
 */
public class PrestitoFactory extends EntityFactory {

	/**
	 * crea un'istanza di Prestito
	 */
	@Override
	public Entity makeElement(HttpServletRequest request) {
		return new Prestito(request);
	}

	@Override
	public Entity makeElement(HttpSession session) {
		// TODO Auto-generated method stub
		return null;
	}

}
