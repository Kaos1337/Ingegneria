package it.univr.is.support;

import it.univr.is.entity.Entity;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Classe di interfaccia delle factory
 * @author marco
 *
 */
public abstract class EntityFactory {

	/**
	 * Ritorna la factory per l'entità richiesta
	 * Ritorna null se non esiste la factory rispettiva
	 * @param string
	 * @return
	 */
	public static EntityFactory getFactory(String string){
		if (string==Constant.UTENTE)
			return new UtenteFactory();
		else if(string==Constant.LIBRO)
			return new LibroFactory();
		else if(string==Constant.LIBROUTENTE)
			return new LibroUtenteFactory();
		else
			return null;
	}
	
	/**
	 * Implementato nei figli per ottenere un'istanza di un'entità
	 * @param request
	 * @return
	 */
	abstract public Entity makeElement(HttpServletRequest request);

	/**
	 * Implementato nei figli per ottenere un'istanza di un'entità
	 * @param session
	 * @return
	 */
	abstract public Entity makeElement(HttpSession session);
		
}
