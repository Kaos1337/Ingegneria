package it.univr.is.support;

import it.univr.is.entity.Entity;
import it.univr.is.entity.Prestito;

import javax.servlet.http.HttpServletRequest;

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

}
