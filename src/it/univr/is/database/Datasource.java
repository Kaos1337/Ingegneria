package it.univr.is.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import it.univr.is.entity.Entity;
import it.univr.is.entity.Libro;
import it.univr.is.entity.LibroUtente;
import it.univr.is.entity.Utente;

public class Datasource {

	private String dburl = "jdbc:postgresql://marretta.it:5432/dbinge";
	private String dbusr = "useringe";
	private String dbpswd = "c2En";
    private String driver = "org.postgresql.Driver";

	public Datasource() {
		try {
			Class.forName(driver);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Esegue la query passata come parametro. Verranno rimpiazzati tutti i '?'
	 * dai i parametri nell'array list
	 * 
	 * @param query
	 *            stringa della query
	 * */
	@SuppressWarnings("unused")
	private ResultSet executeQuery(String query, ArrayList<ParametroSQL> param)
			throws WrongParamNumberSQLException {
		int n = 0;
		for (int i = 0; i < query.length(); i++)
			if (query.charAt(i) == '?')
				n++;
		if (param.size() != n)
			throw new WrongParamNumberSQLException(n, param.size());

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = DriverManager.getConnection(dburl, dbusr, dbpswd);
			pstmt = con.prepareStatement(query);
			pstmt.clearParameters();

			for (int i = 0; i < param.size(); i++)
				if (param.get(i).isString()) // se il parametro è una stringa
					pstmt.setString(i, param.get(i).getStringValue());
				else
					// altrimenti è un intero
					pstmt.setInt(i, param.get(i).getIntValue());

			rs = pstmt.executeQuery();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return rs;
	}

	/**
	 * Effettua un cast da Entity a Utente. Se non va a buon fine ritorna null.
	 * 
	 * @param entity
	 * @return
	 */
	private static Utente checkAndCastUtente(Entity entity) {
		if (entity instanceof Utente)
			return (Utente) entity;
		else
			return null;
	}

	/**
	 * Effettua (nella stessa connessione) un controllo sull'esistenza della
	 * mail e quindi se è assente effettua l'inserimento dei dati dell'utente.
	 * Se l'iscrizione ha successo ritorna true
	 * 
	 * @param utente
	 *            bean Utente con dati iscrizione
	 * @return
	 */
	public boolean checkAndSubscribe(Entity utente) {
		Utente usr = checkAndCastUtente(utente);
		String query = "SELECT u.email FROM utente u where u.email=?";
		boolean nuovoUtente = true;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = DriverManager.getConnection(dburl, dbusr, dbpswd);
			pstmt = con.prepareStatement(query);
			pstmt.clearParameters();
			pstmt.setString(1, usr.getEmail());
			rs = pstmt.executeQuery();
			// la mail non era presente, iscrivo l'utente
			if(rs.next()){
				nuovoUtente = false;
			}
			if (nuovoUtente) {
				query = "INSERT INTO utente(email,nome,cognome,password,via,civico,cap,citta,provincia,dataisc) "
						+ "VALUES(?,?,?,?,?,?,?,?,?,current_date)";
				pstmt = con.prepareStatement(query);
				pstmt.clearParameters();
				pstmt.setString(1, usr.getEmail());
				pstmt.setString(2, usr.getNome());
				pstmt.setString(3, usr.getCognome());
				pstmt.setString(4, usr.getPassword());
				pstmt.setString(5, usr.getVia());
				pstmt.setInt(6, usr.getCivico());
				pstmt.setString(7, usr.getCap());
				pstmt.setString(8, usr.getCitta());
				pstmt.setString(9, usr.getProvincia());
				pstmt.executeQuery();
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return nuovoUtente;
	}

	/**
	 * Effettua un controllo sull'esistenza della mail e se positivo ritorna
	 * true
	 * 
	 * @param email
	 * @return
	 */
	public boolean checkMail(String email) {
		String query = "SELECT u.email FROM utente u where u.email=?";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		boolean presente = true;
		try {
			con = DriverManager.getConnection(dburl, dbusr, dbpswd);
			pstmt = con.prepareStatement(query);
			pstmt.clearParameters();
			pstmt.setString(1, email);
			rs = pstmt.executeQuery();
			if(rs.next())
				presente = false;

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return presente;
	}

	/**
	 * Effettua update della password dell'utente
	 * 
	 * @param utente
	 * @return
	 */
	public void updatePswl(Entity utente) {
		// NON UTILIZZATO NEL PROTOTIPO
		return;
	}

	/**
	 * Se la password attuale corrisponde all'utente attuale posso aggiornare
	 * con i nuovi dati. Se tutto va a buon fine ritorna true
	 * 
	 * @param newUtente
	 *            bean Utente con nuovi dati (quando non null)
	 * @param utenteAttuale
	 *            bean Utente con dati attuali senza password
	 * @param pswAttuale
	 *            psw da verificare
	 * @return
	 */
	public boolean updateUtente(Entity newUtente, Entity utenteAttuale, String pswAttuale) {
		Utente attuale = checkAndCastUtente(utenteAttuale);
		Utente nuovo = checkAndCastUtente(newUtente);
		boolean buonFine = true;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = DriverManager.getConnection(dburl, dbusr, dbpswd);
			// controllo la password
			String query = "select password from utente u where u.id=?";
			pstmt = con.prepareStatement(query);
			pstmt.clearParameters();
			pstmt.setInt(1, attuale.getId());
			rs = pstmt.executeQuery();
			if(rs.next()){
				String psw = rs.getString(1);
				if(! psw.equals(pswAttuale)) // le password sono diverse, non posso aggiornare
					buonFine = false;
				else {
					attuale.setPassword(pswAttuale);
					query = "UPDATE utente SET email=?, nome=?, cognome=?, password=?, via=?, civico=?, cap=?, citta=?, "
							+ "provincia=? WHERE id="+utenteAttuale.getId();
					pstmt = con.prepareStatement(query);
					pstmt.clearParameters();
					// aggiorno tutti i campi, se quelli nuovi sono a null prendo i valori attuali
					pstmt.setString(1, nuovo.getEmail() == null ? attuale.getEmail() : nuovo.getEmail());
					pstmt.setString(2, nuovo.getNome()== null ? attuale.getNome() : nuovo.getNome());
					pstmt.setString(3, nuovo.getCognome()== null ? attuale.getCognome() : nuovo.getCognome());
					pstmt.setString(4, nuovo.getPassword()== null ? attuale.getPassword() : nuovo.getPassword());
					pstmt.setString(5, nuovo.getVia()== null ? attuale.getVia() : nuovo.getVia());
					pstmt.setInt(6, nuovo.getCivico() <= 0 ? attuale.getCivico() : nuovo.getCivico());
					pstmt.setString(7, nuovo.getCap() == null ? attuale.getCap() : nuovo.getCap());
					pstmt.setString(8, nuovo.getCitta() == null ? attuale.getCitta() : nuovo.getCitta());
					pstmt.setString(9, nuovo.getProvincia()== null ? attuale.getProvincia() : nuovo.getProvincia());
					pstmt.executeQuery();
				}
			} else buonFine = false; // non dovrebbe mai accadere, non trova l'id

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return buonFine;
	}

	/**
	 * Inserisce il libro nel database
	 * 
	 * @param libro
	 *            bean Libro con dati da inserire
	 * @param utente
	 *            bean Utente del proprietario, fornisce id e mail
	 */
	public void insertLibro(Entity libro, Entity utente) {
		// TODO Auto-generated method stub

	}

	/**
	 * Ritorna una lista di libriutente filtrata dai parametri non null passati,
	 * se il nome ha valore cerca sia tra nome che cognome dei possibili utenti
	 * 
	 * @param libro
	 * @param nome
	 * @param parameter
	 * @param parameter2
	 * @return
	 */
	public ArrayList<LibroUtente> searchLibri(Entity libro, String nome,
			String citta, String provincia) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Ritorna la lista di libri dell'utente
	 * 
	 * @param id
	 * @return
	 */
	public ArrayList<Libro> searchLibri(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Motodo per verifica credenziali di login, nel nostro caso verifica email
	 * e psw. Ritorna Utente null se nessuna corrispondenza
	 * 
	 * @param utente
	 *            Bean Utente
	 * @return Utente loggato ma con campo psw=null
	 */
	public Utente login(Entity utente) {
		String query = "select * from utente where email=? and password=?";
		
		Utente login = checkAndCastUtente(utente);
		Utente loggato = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = DriverManager.getConnection(dburl, dbusr, dbpswd);
			pstmt = con.prepareStatement(query);
			pstmt.clearParameters();
			pstmt.setString(1, login.getEmail());
			pstmt.setString(2, login.getPassword());
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				loggato = new Utente();
				loggato.setId(rs.getInt(1));
				loggato.setEmail(rs.getString(2));
				loggato.setNome(rs.getString(3));
				loggato.setCognome(rs.getString(4));
				loggato.setPassword(null); // password a null
				loggato.setVia(rs.getString(6));
				loggato.setCivico(rs.getInt(7));
				loggato.setCap(rs.getString(8));
				loggato.setCitta(rs.getString(9));
				loggato.setProvincia(rs.getString(10));
				loggato.setDataisc(rs.getString(11));
				loggato.setRuolo(rs.getInt(12));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return loggato;
	}

	/**
	 * Metodo per aggiornare i libri forniti con l'operazione indicata, se
	 * l'operazione è un prestito inserisce anche una tupla nella relativa
	 * tabella
	 * 
	 * @param select
	 * @param op
	 */
	public void updateLibri(String[] select, int op) {
		// TODO Auto-generated method stub

	}

	/**
	 * Metodo che recupera i dati dell'utente
	 * 
	 * @param id
	 * @return
	 */
	public Utente getUtente(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Metodo che recupera i dati delle prenotazioni e iscrizioni assolute e li
	 * immette in un array secondo il formato: col.anno/mese n.iscritti
	 * n.prenotazioni {{ 2010/1 , .......... , ................}, { 2010/2 ,
	 * .......... , ...............},..}
	 * 
	 * @param tutti_mesi
	 *            ArrayList di stringhe formato: aaaa-mm
	 * @return arraybidimensionale di dimensioni [lunghezza tutti_mesi][3]
	 */
	public String[][] getStatAssolute(ArrayList<String> tutti_mesi) {
		// Nota: è necessario che esista per ogni giorno una riga,
		// se non sono presenti risultati per un giorno
		// porre una riga con valori numerici 0 nei conteggi

		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Metodo che recupera i dati delle prenotazioni e iscrizioni mensili e li
	 * immette in un array secondo il formato: anno/mese/giorno n.iscritti
	 * n.prenotazioni {{ 2010/1/1 , .......... , ................}, { 2010/1/2 ,
	 * .......... , ...............},..}
	 * 
	 * @param trenta_giorni_da_oggi
	 *            ArrayList di stringhe formato: aaaa-mm-gg
	 * @return arraybidimensionale di dimensioni [lunghezza
	 *         trenta_giorni_da_oggi][3]
	 */
	public String[][] getStatMensili(ArrayList<String> trenta_giorni_da_oggi) {
		// Nota: è necessario che esista per ogni giorno una riga,
		// se non sono presenti risultati per un giorno
		// porre una riga con valori numerici 0 nei conteggi
		//
		// TODO Auto-generated method stub
		return null;
	}

}
