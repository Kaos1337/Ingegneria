package it.univr.is.database;

import java.util.ArrayList;

import it.univr.is.entity.Libro;
import it.univr.is.entity.LibroConSelezione;
import it.univr.is.entity.Utente;

public class Datasource {

	/**
	 * Effettua (nella stessa connessione) un controllo sull'esistenza
	 * della mail e quindi se è assente effettua l'inserimento
	 * dei dati dell'utente. Se l'iscrizione ha successo ritorna true
	 * @param utente
	 * @return
	 */
	public boolean checkAndSubscribe(Utente utente) {
		// TODO Auto-generated method stub
		return false;
	}
	
	/**
	 * Effettua un controllo sull'esistenza della mail
	 * e se positivo ritorna true
	 * @param email
	 * @return
	 */
	public boolean checkMail(String email) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * Effettua update della password dell'utente e
	 * se l'operazione ha successo ritorna true
	 * @param utente
	 * @return
	 */
	public boolean updatePswl(Utente utente) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 *---------NON DEFINITIVA--------
	 * Se la password attuale corrisponde all'utente attuale
	 * posso aggiornare con i nuovi dati. Se tutto va a buon fine
	 * ritorna true
	 * @param newutente
	 * @param actualutente
	 * @return
	 */
	public boolean updateUtente(Utente newUtente, Utente actualUtente ) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * Inserisce il libro nel database
	 * @param libro
	 */
	public void insertLibro(Libro libro) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Ritorna una lista di libri
	 * filtrata dai parametri non null del libro passato
	 * @param libro
	 * @return
	 */
	public ArrayList<Libro> searchLibri(Libro libro) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Ritorna una lista di libri
	 * filtrata dai parametri non null passati
	 * @param libro
	 * @param parameter
	 * @param parameter2
	 * @return 
	 */
	public ArrayList<Libro> searchLibri(Libro libro, String citta, String provincia) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Ritorna la lista di libri dell'utente
	 * @param libro
	 * @return
	 */
	public ArrayList<Libro> searchLibri(String email) {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * Motodo per verifica credenziali di login email e psw.
	 * Ritorna Utente null se nessuna corrispondenza
	 * @param utente
	 * @return Utente loggato
	 */
	public Utente login(Utente utente) {
		// TODO Auto-generated method stub
		return null;
	}

	

}
