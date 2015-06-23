package it.univr.is.support;

import it.univr.is.entity.Entity;
import it.univr.is.entity.Utente;

import javax.servlet.http.HttpServletRequest;

/**
 * Factory di entità Utente
 * @author marco
 *
 */
public class UtenteFactory extends EntityFactory {

	
	/**
	 * crea un'istanza di Utente
	 */
	@Override
	public Entity makeElement(HttpServletRequest request) {
		return new Utente(request);
	}

}
