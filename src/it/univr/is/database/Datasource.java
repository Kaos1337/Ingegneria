package it.univr.is.database;

import java.util.ArrayList;

import it.univr.is.entity.Entity;
import it.univr.is.entity.Libro;
import it.univr.is.entity.LibroUtente;
import it.univr.is.entity.Utente;

public class Datasource {

	/**
	 * Effettua (nella stessa connessione) un controllo sull'esistenza
	 * della mail e quindi se è assente effettua l'inserimento
	 * dei dati dell'utente. Se l'iscrizione ha successo ritorna true
	 * @param utente bean Utente con dati iscrizione
	 * @return
	 */
	public boolean checkAndSubscribe(Entity utente) {
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
	 * Effettua update della password dell'utente
	 * 
	 * @param utente
	 * @return
	 */
	public void updatePswl(Entity utente) {
		// TODO Auto-generated method stub
		// LASCIA STARE, METODO MAI USATO NEL PROTOTIPO
	}

	/**
	 * Se la password attuale corrisponde all'utente attuale
	 * posso aggiornare con i nuovi dati. Se tutto va a buon fine
	 * ritorna true
	 * @param newUtente bean Utente con nuovi dati (quando non null)
	 * @param utenteAttuale bean Utente con dati attuali senza password
	 * @param pswAttuale psw da verificare
	 * @return
	 */
	 
	public boolean updateUtente(Entity newUtente, Entity utenteAttuale, String pswAttuale ) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * Inserisce il libro nel database
	 * @param libro bean Libro con dati da inserire
	 * @param utente bean Utente del proprietario, fornisce id e mail
	 */
	public void insertLibro(Entity libro, Entity utente) {
		// TODO Auto-generated method stub
		
	}


	/**
	 * Ritorna una lista di libriutente
	 * filtrata dai parametri non null passati,
	 * se il nome ha valore cerca sia tra nome che cognome
	 * dei possibili utenti
	 * @param libro
	 * @param nome
	 * @param parameter
	 * @param parameter2
	 * @return 
	 */
	public ArrayList<LibroUtente> searchLibri(Entity libro,String nome, String citta, String provincia) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Ritorna la lista di libri dell'utente
	 * @param id
	 * @return
	 */
	public ArrayList<Libro> searchLibri(int id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * Motodo per verifica credenziali di login, nel nostro 
	 * caso verifica email e psw.
	 * Ritorna Utente null se nessuna corrispondenza
	 * @param utente Bean Utente
	 * @return Utente loggato ma con campo psw=null
	 */
	public Utente login(Entity utente) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Metodo per aggiornare i libri forniti 
	 * con l'operazione indicata, se l'operazione è un prestito 
	 * inserisce anche una tupla nella relativa tabella
	 * @param select
	 * @param op
	 */
	public void updateLibri(String[] select, int op) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Metodo che recupera i dati dell'utente
	 * @param id
	 * @return
	 */
	public Utente getUtente(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Metodo che recupera i dati delle prenotazioni e iscrizioni
	 * assolute e li immette in un array secondo il formato:
	 * 	col.anno/mese	 n.iscritti		n.prenotazioni
	 * {{ 2010/1 , .......... , ................},
	 *  { 2010/2 , .......... , ...............},..}
	 *  
	 * @param tutti_mesi  ArrayList di stringhe formato: aaaa-mm
	 * @return arraybidimensionale di dimensioni [lunghezza tutti_mesi][3]
	 */
	public String[][] getStatAssolute(ArrayList<String> tutti_mesi) {
	//  Nota: è necessario che esista per ogni giorno una riga,
	//  		se non sono presenti risultati per un giorno
	//  		porre una riga con valori numerici 0 nei conteggi
			
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Metodo che recupera i dati delle prenotazioni e iscrizioni
	 * mensili e li immette in un array secondo il formato:
	 * anno/mese/giorno	 n.iscritti		n.prenotazioni
	 * {{ 2010/1/1 , .......... , ................},
	 *  { 2010/1/2 , .......... , ...............},..}
	 *  
	 * @param trenta_giorni_da_oggi ArrayList di stringhe formato: aaaa-mm-gg
	 * @return arraybidimensionale di dimensioni [lunghezza trenta_giorni_da_oggi][3]
	 */
	public String[][] getStatMensili(ArrayList<String> trenta_giorni_da_oggi) {
		//  Nota: è necessario che esista per ogni giorno una riga,
		//  		se non sono presenti risultati per un giorno
		//  		porre una riga con valori numerici 0 nei conteggi
		//  
		// TODO Auto-generated method stub
		return null;
	}

	

}
