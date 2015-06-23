package it.univr.is.database;

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

}
