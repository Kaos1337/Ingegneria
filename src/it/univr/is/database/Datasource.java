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
	 * Ritorna una lista di libri con campo di selezione false 
	 * filtrata dai parametri non null del libro passato
	 * @param libro
	 * @return
	 */
	public ArrayList<LibroConSelezione> searchLibri(Libro libro) {
		// TODO Auto-generated method stub
		return null;
	}

}
