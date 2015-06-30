package it.univr.is.support;

import it.univr.is.entity.Entity;
import it.univr.is.entity.Utente;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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

	/**
	 * crea un'istanza di Utente
	 */
	@Override
	public Entity makeElement(HttpSession session) {
		return new Utente(session);
		
	}

}
