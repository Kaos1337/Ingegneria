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
	 * Effettua un cast da Entity a Libro. Se non va a buon fine ritorna null.
	 * 
	 * @param entity
	 * @return
	 */
	private static Libro checkAndCastLibro(Entity entity) {
		if (entity instanceof Libro)
			return (Libro) entity;
		else
			return null;
	}

	/**
	 * Effettua (nella stessa connessione) un controllo sull'esistenza della mail e quindi se è assente
	 * effettua l'inserimento dei dati dell'utente. Se l'iscrizione ha successo ritorna true
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
			if (rs.next()) {
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
				pstmt.setString(9, usr.getProvincia().toUpperCase());
				pstmt.executeUpdate();
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
	 * Effettua un controllo sull'esistenza della mail e se positivo ritorna true
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
			if (rs.next())
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
		// TODO NON UTILIZZATO NEL PROTOTIPO
		return;
	}

	/**
	 * Se la password attuale corrisponde all'utente attuale posso aggiornare con i nuovi dati. Se tutto va a
	 * buon fine ritorna true
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
			if (rs.next()) {
				String psw = rs.getString(1);
				if (!psw.equals(pswAttuale)) // le password sono diverse, non posso aggiornare
					buonFine = false;
				else {
					attuale.setPassword(pswAttuale);
					query = "UPDATE utente SET email=?, nome=?, cognome=?, password=?, via=?, civico=?, cap=?, citta=?, "
							+ "provincia=? WHERE id=" + utenteAttuale.getId();
					pstmt = con.prepareStatement(query);
					pstmt.clearParameters();
					// aggiorno tutti i campi, se quelli nuovi sono a null prendo i valori attuali
					pstmt.setString(1, nuovo.getEmail() == null ? attuale.getEmail() : nuovo.getEmail());
					pstmt.setString(2, nuovo.getNome() == null ? attuale.getNome() : nuovo.getNome());
					pstmt.setString(3, nuovo.getCognome() == null ? attuale.getCognome() : nuovo.getCognome());
					pstmt.setString(4,
							nuovo.getPassword().equals("") ? attuale.getPassword() : nuovo.getPassword());
					pstmt.setString(5, nuovo.getVia() == null ? attuale.getVia() : nuovo.getVia());
					pstmt.setInt(6, nuovo.getCivico() <= 0 ? attuale.getCivico() : nuovo.getCivico());
					pstmt.setString(7, nuovo.getCap() == null ? attuale.getCap() : nuovo.getCap());
					pstmt.setString(8, nuovo.getCitta() == null ? attuale.getCitta() : nuovo.getCitta());
					pstmt.setString(9,
							nuovo.getProvincia() == null ? attuale.getProvincia() : nuovo.getProvincia());
					pstmt.executeUpdate();
				}
			} else
				buonFine = false; // non dovrebbe mai accadere, non trova l'id

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
		Libro l = checkAndCastLibro(libro);
		Utente u = checkAndCastUtente(utente);

		String query = "INSERT INTO libro(titolo, utente, autore, "
				+ "categoria, categoria2, stato, edizione, isbn, copertina) "
				+ "VALUES(?,?,?,?,?,0,?,?,?)";
		//disponibile, prenotato, non disponibile(non in prestito), eliminato
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DriverManager.getConnection(dburl, dbusr, dbpswd);
			pstmt = con.prepareStatement(query);
			pstmt.clearParameters();

			pstmt.setString(1, l.getTitolo());
			pstmt.setInt(2, u.getId());
			pstmt.setString(3, l.getAutore());
			pstmt.setString(4, l.getCategoria());
			pstmt.setString(5, l.getCategoria2());
			pstmt.setString(6, l.getEdizione());
			pstmt.setString(7, l.getIsbn());
			pstmt.setString(8, l.getCopertina());
			pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	/**
	 * Ritorna una lista di libriutente filtrata dai parametri non null passati, se il nome ha valore cerca
	 * sia tra nome che cognome dei possibili utenti. I libri devono essere disponibili.
	 * 
	 * @param libro
	 * @param nome nome del proprietario (da splittare)
	 * @param citta citta del proprietario
	 * @param provincia provincia del proprietario
	 * @return
	 */
	public ArrayList<LibroUtente> searchLibri(Entity libro, String nome, String citta, String provincia) {
		Libro l = checkAndCastLibro(libro);
		String query = "SELECT DISTINCT l.stato, l.id, l.titolo, l.utente, l.autore, l.categoria,"
				+ " l.categoria2, l.stato, l.edizione, l.isbn, l.copertina,"
				+ " u.nome, u.cognome, u.citta, u.provincia"
				+ " FROM libro l JOIN utente u on l.utente = u.id "
				+ "WHERE u.citta ILIKE ? and u.provincia ILIKE ? "
				+ "and l.titolo ILIKE ? and l.autore ILIKE ? and l.categoria ILIKE ? and l.categoria2 ILIKE ?"
				+ " and l.edizione ILIKE ? and l.isbn ILIKE ?";
		String qnome = " and (u.nome ILIKE ? or u.cognome ILIKE ?)";
		String[] nomi = nome.equals("") ? new String[]{} : nome.split(" ");
		
		int n;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<LibroUtente> result = new ArrayList<LibroUtente>();
		try {
			con = DriverManager.getConnection(dburl, dbusr, dbpswd);
			
			for(int i = 0; i < nomi.length; i++)
				query += qnome;
			
			query += " order by l.titolo";

			pstmt = con.prepareStatement(query);
			pstmt.clearParameters();
			
			n = 1;
			pstmt.setString(n++, citta.equals("") ? "%" : "%" + citta + "%");
			pstmt.setString(n++, provincia.equals("") ? "%" : provincia.toUpperCase());
			pstmt.setString(n++, l.getTitolo().equals("") ? "%" : "%" + l.getTitolo() + "%");
			pstmt.setString(n++, l.getAutore().equals("") ? "%" : "%" + l.getAutore() + "%");
			pstmt.setString(n++, l.getCategoria().equals("") ? "%" : "%" + l.getCategoria() + "%");
			pstmt.setString(n++, l.getCategoria2().equals("") ? "%" : "%" + l.getCategoria2() + "%");
			pstmt.setString(n++, l.getEdizione().equals("") ? "%" : "%" + l.getEdizione() + "%");
			pstmt.setString(n++, l.getIsbn().equals("") ? "%" : "%" + l.getIsbn() + "%");
			
			for(int i = 0; i < nomi.length; i++){
				pstmt.setString(n++, nomi[i]);
				pstmt.setString(n++, nomi[i]);
			}
			
			//System.out.println(pstmt);
			
			rs = pstmt.executeQuery();
			while(rs.next()){
				LibroUtente li = new LibroUtente();
				n = 1;
				if(rs.getInt(n++) != 0) // il libro non è dispobibile
					continue;
				li.setId(rs.getInt(n++));
				li.setTitolo(rs.getString(n++));
				li.setUtente(rs.getInt(n++));
				li.setAutore(rs.getString(n++));
				li.setCategoria(rs.getString(n++));
				li.setCategoria2(rs.getString(n++));
				li.setStato(rs.getInt(n++));
				li.setEdizione(rs.getString(n++));
				li.setIsbn(rs.getString(n++));
				li.setCopertina(rs.getString(n++));
				li.setNome(rs.getString(n++));
				li.setCognome(rs.getString(n++));
				li.setCitta(rs.getString(n++));
				li.setProvincia(rs.getString(n++));
				result.add(li);
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

		return result;
	}

	/**
	 * Ritorna la lista di libri dell'utente
	 * 
	 * @param id
	 * @return
	 */
	public ArrayList<Libro> searchLibri(int id) {
		String query = "SELECT * FROM libro l WHERE l.utente=? ORDER BY l.titolo";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<Libro> result = new ArrayList<Libro>();
		try {
			con = DriverManager.getConnection(dburl, dbusr, dbpswd);
			pstmt = con.prepareStatement(query);
			pstmt.clearParameters();
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			while(rs.next()){
				if(rs.getInt(7) == 3) // libro eliminato
					continue;
				Libro libro = new Libro();
				libro.setId(rs.getInt(1));
				libro.setTitolo(rs.getString(2));
				libro.setUtente(rs.getInt(3));
				libro.setAutore(rs.getString(4));
				libro.setCategoria(rs.getString(5));
				libro.setCategoria2(rs.getString(6));
				libro.setStato(rs.getInt(7));
				libro.setEdizione(rs.getString(8));
				libro.setIsbn(rs.getString(9));
				libro.setCopertina(rs.getString(10));
				result.add(libro);
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
		return result;
	}

	/**
	 * Motodo per verifica credenziali di login, nel nostro caso verifica email e psw. Ritorna Utente null se
	 * nessuna corrispondenza
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

			if (rs.next()) {
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
	 * Metodo per aggiornare i libri forniti con l'operazione indicata, se l'operazione è un prestito
	 * inserisce anche una tupla nella relativa tabella
	 * 
	 * @param select contiene gli id dei libri
	 * @param statoNuovo operazione da eseguire
	 */
	public void updateLibri(String[] select, int statoNuovo) {
		String query;		
		int statoPrecedente = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = DriverManager.getConnection(dburl, dbusr, dbpswd);
			
			//disponibile, prenotato, non disponibile(non in prestito), eliminato
			
			for(int i = 0; i < select.length; i++){
				// controllo lo stato attuale del libro, se era prenotato
				// aggiungo la data di fine del prestito
				
				// se è stato prenotato due volte lo stesso libro, l'utente si è sbagliato
				query = "SELECT l.stato FROM libro l WHERE l.id=?";
				pstmt = con.prepareStatement(query);
				pstmt.clearParameters();
				pstmt.setInt(1, Integer.parseInt(select[i]));
				rs = pstmt.executeQuery();
				
				if(rs.next())// sempre vero
					statoPrecedente = rs.getInt(1);

				if(statoNuovo != 1){ // non deve essere prenotato
					if(statoPrecedente == 1){ // era prenotato!
						// aggiungo la data di fine prestito
						query = "UPDATE prestito SET dataf=current_date WHERE dataf is NULL and id_libro="+select[i];
						pstmt = con.prepareStatement(query);
						pstmt.clearParameters();
						pstmt.executeUpdate();
					}
				} else {
					// aggiungo una prenotazione se prima non era prenotato
					if(statoPrecedente != 1){
						query = "INSERT INTO prestito (id_libro, datai) VALUES(?, current_date)";
						pstmt = con.prepareStatement(query);
						pstmt.clearParameters();
						pstmt.setInt(1, Integer.parseInt(select[i]));
						pstmt.executeUpdate();
					}
				}
				
				// cambio infine lo stato del libro
				query = "UPDATE libro SET stato=" + statoNuovo + " WHERE id=" + select[i];
				pstmt = con.prepareStatement(query);
				pstmt.clearParameters();
				pstmt.executeUpdate();
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
	}

	/**
	 * Metodo che recupera i dati dell'utente
	 * 
	 * @param id
	 * @return
	 */
	public Utente getUtente(int id) {
		// TODO String query = "SELECT * FROM utente u WHERE u.id=?";
		// NON UTILIZZATO NEL PROTOTIPO
		return null;
	}

	private int nGiorniMese(String aaaamm){
		String[] ma = aaaamm.split("-");
		
		int anno = Integer.parseInt(ma[0]);
		int mese = Integer.parseInt(ma[1]);
		
		if(mese == 10 || mese == 4 || mese == 6 || mese == 9)
			return 30;
		if (mese==2)
			return (anno % 400 == 0 || (anno % 100 != 0 && anno % 4 == 0)) ? 29 : 28;
		return 31;
	}
	
	/**
	 * Metodo che recupera i dati delle prenotazioni e iscrizioni assolute e li immette in un array secondo il
	 * formato:
	 * col.anno/mese n.iscritti n.prenotazioni
	 * {
	 *   { 2010-01 , n_iscritti_2010-01 , n_prenotazioni_2010-01},
	 *   { 2010-02 , .......... , ...............},
	 * ..}
	 * 
	 * @param tutti_mesi
	 *            ArrayList di stringhe formato: aaaa-mm
	 * @return arraybidimensionale di dimensioni [lunghezza tutti_mesi][3]
	 */
	public String[][] getStatAssolute(ArrayList<String> tuttiMesi) {
		// TODO http://www.w3schools.com/sql/sql_dates.asp
		// Nota: è necessario che esista per ogni giorno una riga,
		// se non sono presenti risultati per un giorno
		// porre una riga con valori numerici 0 nei conteggi
		for(int i = 0; i < tuttiMesi.size(); i++){
			String fineMese = tuttiMesi.get(i)+"-"+nGiorniMese(tuttiMesi.get(i));
			String inizioMese = tuttiMesi.get(i)+"-01";
			String query = "SELECT count(*) FROM utente u WHERE u.dataisc>='" + inizioMese + "' and u.dataisc<='" + fineMese + "'";
			System.out.println(query);
		}
		
		return null;
	}
/*
	public static void main(String[] args){
		ArrayList<String> tuttiMesi = new ArrayList<String>();
		tuttiMesi.add("2015-07");
		new Datasource().getStatAssolute(tuttiMesi);
	}
*/	
	/**
	 * Metodo che recupera i dati delle prenotazioni e iscrizioni mensili e li immette in un array secondo il
	 * formato: anno/mese/giorno n.iscritti n.prenotazioni
	 * {{ 2010/1/1 , .......... , ................},
	 * {2010/1/2 , .......... , ...............},..}
	 * 
	 * @param trenta_giorni_da_oggi
	 *            ArrayList di stringhe formato: aaaa-mm-gg
	 * @return arraybidimensionale di dimensioni [lunghezza trenta_giorni_da_oggi][3]
	 */
	public String[][] getStatMensili(ArrayList<String> trenta_giorni_da_oggi) {
		// Nota: è necessario che esista per ogni giorno una riga,
		// se non sono presenti risultati per un giorno
		// porre una riga con valori numerici 0 nei conteggi
		// TODO
		/*String query = "SELECT dataisc from utente u where current_date-u.dataisc<=30";
		query = "SELECT datai FROM prestito p WHERE current_date-p.datai<=30";*/
		//SELECT count(*) from utente u where current_date-u.dataisc<=30;

		
		return null;
	}

}
