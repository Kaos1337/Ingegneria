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
	 * @param e
	 * @return
	 */
	public static EntityFactory getFactory(Entity e){
		if (e.getClass()==Utente.class)
			return new UtenteFactory();
		else if(e.getClass()==Libro.class)
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
