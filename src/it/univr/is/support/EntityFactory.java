package it.univr.is.support;

import it.univr.is.entity.Entity;
import it.univr.is.entity.Libro;
import it.univr.is.entity.Utente;

import javax.servlet.http.HttpServletRequest;

/**
 * Classe di interfaccia delle factory
 * @author marco
 *
 */
public abstract class EntityFactory {

	/**
	 * Ritorna la factory per l'entità fornita
	 * @param string
	 * @return
	 */
	public static EntityFactory getFactory(String string){
		if (string=="UTENTE")
			return new UtenteFactory();
		else if(string=="LIBRO")
			return new LibroFactory();
		else
			return new PrestitoFactory();
	}
	
	/**
	 * Implementato nei figli per ottenere un'istanza di un'entità
	 * @param request
	 * @return
	 */
	abstract public Entity makeElement(HttpServletRequest request);
}
