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

			if (rs.next()) // se la query ritorna un valore allora l'utente era già iscritto
				nuovoUtente = false;
			else { // altrimenti lo iscrivo
				query = "INSERT INTO utente(email,nome,cognome,password,via,civico,cap,citta,provincia,dataisc) "
						+ "VALUES(?,?,?,?,?,?,?,?,?,current_date)";
				pstmt = con.prepareStatement(query);
				pstmt.clearParameters();
				int n = 1;
				pstmt.setString(n++, usr.getEmail());
				pstmt.setString(n++, usr.getNome());
				pstmt.setString(n++, usr.getCognome());
				pstmt.setString(n++, usr.getPassword());
				pstmt.setString(n++, usr.getVia());
				pstmt.setInt(n++, usr.getCivico());
				pstmt.setString(n++, usr.getCap());
				pstmt.setString(n++, usr.getCitta());
				pstmt.setString(n, usr.getProvincia().toUpperCase());
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
	 * Elimina un utente data la mail Usata solo nel test
	 * */
	public void eliminaUtente(Entity utente) {
		Utente usr = checkAndCastUtente(utente);
		String query = "delete from utente where email=?";

		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DriverManager.getConnection(dburl, dbusr, dbpswd);
			pstmt = con.prepareStatement(query);
			pstmt.clearParameters();
			pstmt.setString(1, usr.getEmail());
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
					int n = 1;
					pstmt.setString(n++, nuovo.getEmail() == null ? attuale.getEmail() : nuovo.getEmail());
					pstmt.setString(n++, nuovo.getNome() == null ? attuale.getNome() : nuovo.getNome());
					pstmt.setString(n++,
							nuovo.getCognome() == null ? attuale.getCognome() : nuovo.getCognome());
					pstmt.setString(n++,
							nuovo.getPassword().equals("") ? attuale.getPassword() : nuovo.getPassword());
					pstmt.setString(n++, nuovo.getVia() == null ? attuale.getVia() : nuovo.getVia());
					pstmt.setInt(n++, nuovo.getCivico() <= 0 ? attuale.getCivico() : nuovo.getCivico());
					pstmt.setString(n++, nuovo.getCap() == null ? attuale.getCap() : nuovo.getCap());
					pstmt.setString(n++, nuovo.getCitta() == null ? attuale.getCitta() : nuovo.getCitta());
					pstmt.setString(n++,
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
				+ "categoria, categoria2, stato, edizione, isbn, copertina) VALUES(?,?,?,?,?,0,?,?,?)";
		// disponibile, prenotato, non disponibile(non in prestito), eliminato
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DriverManager.getConnection(dburl, dbusr, dbpswd);
			pstmt = con.prepareStatement(query);
			pstmt.clearParameters();
			int n = 1;
			pstmt.setString(n++, l.getTitolo());
			pstmt.setInt(n++, u.getId());
			pstmt.setString(n++, l.getAutore());
			pstmt.setString(n++, l.getCategoria());
			pstmt.setString(n++, l.getCategoria2());
			pstmt.setString(n++, l.getEdizione());
			pstmt.setString(n++, l.getIsbn());
			pstmt.setString(n++, l.getCopertina());
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
	 * @param nome
	 *            nome del proprietario (da splittare)
	 * @param citta
	 *            citta del proprietario
	 * @param provincia
	 *            provincia del proprietario
	 * @return
	 */
	public ArrayList<LibroUtente> searchLibri(Entity libro, String nome, String citta, String provincia) {
		Libro l = checkAndCastLibro(libro);
		String query = "SELECT DISTINCT l.id, l.titolo, l.utente, l.autore, l.categoria,"
				+ " l.categoria2, l.stato, l.edizione, l.isbn, l.copertina,"
				+ " u.nome, u.cognome, u.citta, u.provincia"
				+ " FROM libro l JOIN utente u on l.utente = u.id "
				+ "WHERE l.stato = 0 and u.citta ILIKE ? and u.provincia ILIKE ? "
				+ "and l.titolo ILIKE ? and l.autore ILIKE ? and "
				+ "((l.categoria ILIKE ? and l.categoria2 ILIKE ?) or (l.categoria ILIKE ? and l.categoria2 ILIKE ?))"
				+ " and l.edizione ILIKE ? and l.isbn ILIKE ?";
		String qnome = " and (u.nome ILIKE ? or u.cognome ILIKE ?)";
		String[] nomi = nome.equals("") ? new String[] {} : nome.split(" ");

		int n;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<LibroUtente> result = new ArrayList<LibroUtente>();
		try {
			con = DriverManager.getConnection(dburl, dbusr, dbpswd);

			for (int i = 0; i < nomi.length; i++)
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
			pstmt.setString(n++, l.getCategoria2().equals("") ? "%" : "%" + l.getCategoria2() + "%");
			pstmt.setString(n++, l.getCategoria().equals("") ? "%" : "%" + l.getCategoria() + "%");

			pstmt.setString(n++, l.getEdizione().equals("") ? "%" : "%" + l.getEdizione() + "%");
			pstmt.setString(n++, l.getIsbn().equals("") ? "%" : "%" + l.getIsbn() + "%");

			for (int i = 0; i < nomi.length; i++) {
				pstmt.setString(n++, nomi[i]);
				pstmt.setString(n++, nomi[i]);
			}

			rs = pstmt.executeQuery();

			while (rs.next()) {
				LibroUtente li = new LibroUtente();
				n = 1;
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
				li.setProvincia(rs.getString(n));
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
		String query = "SELECT * FROM libro l WHERE l.utente=? and stato<>3 ORDER BY l.titolo";
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
			while (rs.next()) {
				Libro libro = new Libro();
				int n = 1;
				libro.setId(rs.getInt(n++));
				libro.setTitolo(rs.getString(n++));
				libro.setUtente(rs.getInt(n++));
				libro.setAutore(rs.getString(n++));
				libro.setCategoria(rs.getString(n++));
				libro.setCategoria2(rs.getString(n++));
				libro.setStato(rs.getInt(n++));
				libro.setEdizione(rs.getString(n++));
				libro.setIsbn(rs.getString(n++));
				libro.setCopertina(rs.getString(n));
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
				int n = 1;
				loggato.setId(rs.getInt(n++));
				loggato.setEmail(rs.getString(n++));
				loggato.setNome(rs.getString(n++));
				loggato.setCognome(rs.getString(n++));
				loggato.setPassword(null);
				n++;// password a null
				loggato.setVia(rs.getString(n++));
				loggato.setCivico(rs.getInt(n++));
				loggato.setCap(rs.getString(n++));
				loggato.setCitta(rs.getString(n++));
				loggato.setProvincia(rs.getString(n++));
				loggato.setDataisc(rs.getString(n++));
				loggato.setRuolo(rs.getInt(n));
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
	 * @param select
	 *            contiene gli id dei libri
	 * @param statoNuovo
	 *            operazione da eseguire
	 */
	@SuppressWarnings("resource")
	public void updateLibri(String[] select, int statoNuovo) {
		String query;
		int statoPrecedente = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = DriverManager.getConnection(dburl, dbusr, dbpswd);

			// disponibile, prenotato, non disponibile(non in prestito), eliminato

			for (int i = 0; i < select.length; i++) {
				// controllo lo stato attuale del libro, se era prenotato
				// aggiungo la data di fine del prestito

				// se è stato prenotato due volte lo stesso libro, l'utente si è sbagliato
				query = "SELECT l.stato FROM libro l WHERE l.id=?";
				pstmt = con.prepareStatement(query);
				pstmt.clearParameters();
				pstmt.setInt(1, Integer.parseInt(select[i]));
				rs = pstmt.executeQuery();

				if (rs.next())// sempre vero
					statoPrecedente = rs.getInt(1);

				// ora è prenotato e il suo stato cambia
				if (statoNuovo != statoPrecedente && statoPrecedente == 1) {
					// aggiungo la data di fine prestito
					query = "UPDATE prestito SET dataf=current_date WHERE dataf is NULL and id_libro="
							+ select[i];
					pstmt = con.prepareStatement(query);
					pstmt.clearParameters();
					pstmt.executeUpdate();
				} else if (statoPrecedente != 1 && statoNuovo == 1) {
					// il libro diventa prenotato
					query = "INSERT INTO prestito (id_libro, datai) VALUES(" + select[i] + ", current_date)";
					pstmt = con.prepareStatement(query);
					pstmt.clearParameters();
					pstmt.executeUpdate();
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
		String query = "SELECT * FROM utente u WHERE u.id=?";

		Utente loggato = new Utente();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = DriverManager.getConnection(dburl, dbusr, dbpswd);
			pstmt = con.prepareStatement(query);
			pstmt.clearParameters();
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				loggato = new Utente();
				int n = 1;
				loggato.setId(rs.getInt(n++));
				loggato.setEmail(rs.getString(n++));
				loggato.setNome(rs.getString(n++));
				loggato.setCognome(rs.getString(n++));
				loggato.setPassword(null);
				n++;// password a null
				loggato.setVia(rs.getString(n++));
				loggato.setCivico(rs.getInt(n++));
				loggato.setCap(rs.getString(n++));
				loggato.setCitta(rs.getString(n++));
				loggato.setProvincia(rs.getString(n++));
				loggato.setDataisc(rs.getString(n++));
				loggato.setRuolo(rs.getInt(n));
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
	 * Metodo che recupera i dati delle prenotazioni e iscrizioni assolute e li immette in un array secondo il
	 * formato: col.anno/mese n.iscritti n.prenotazioni { { 2010-01 , n_iscritti_2010-01 ,
	 * n_prenotazioni_2010-01}, { 2010-02 , .......... , ...............}, ..}
	 * 
	 * @param tutti_mesi
	 *            ArrayList di stringhe formato: aaaa-mm-01 con ultima data rappresentante il mese successivo
	 *            oggi, da escludere nel ritorno
	 * @return arraybidimensionale di dimensioni [lunghezza tutti_mesi-1][3]
	 */
	public String[][] getStatAssolute(ArrayList<String> tuttiMesi) {
		// Nota: è necessario che esista per ogni giorno una riga,
		// se non sono presenti risultati per un giorno
		// porre una riga con valori numerici 0 nei conteggi

		int mesiDaProcessare = tuttiMesi.size() - 1;

		String[][] res = new String[mesiDaProcessare][];

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			con = DriverManager.getConnection(dburl, dbusr, dbpswd);
			for (int i = 0; i < mesiDaProcessare; i++) {

				ArrayList<String> t = new ArrayList<String>();
				t.add(tuttiMesi.get(i));

				String inizioMese = tuttiMesi.get(i);
				// all'ultimo ciclo qui viene utilizzato il mese in più
				String inizioMeseSucc = tuttiMesi.get(i + 1);

				String query1 = "SELECT count(*) FROM utente u WHERE u.dataisc>='" + inizioMese
						+ "' and u.dataisc<'" + inizioMeseSucc + "'";
				pstmt = con.prepareStatement(query1);
				rs = pstmt.executeQuery();

				if (rs.next()) // magari 0, ma comunque sempre vero
					t.add(rs.getString(1));

				String query2 = "SELECT count(*) FROM prestito p WHERE p.datai>='" + inizioMese
						+ "' and p.datai<'" + inizioMeseSucc + "'";
				pstmt = con.prepareStatement(query2);
				rs = pstmt.executeQuery();

				if (rs.next()) // magari 0, ma comunque sempre vero
					t.add(rs.getString(1));

				res[i] = new String[3];
				res[i] = t.toArray(res[i]);
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

		return res;
	}

	/**
	 * Metodo che recupera i dati delle prenotazioni e iscrizioni mensili e li immette in un array secondo il
	 * formato: anno/mese/giorno n.iscritti n.prenotazioni {{ 2010/1/1 , .......... , ................},
	 * {2010/1/2 , .......... , ...............},..}
	 * 
	 * @param trenta_giorni_da_oggi
	 *            ArrayList di stringhe formato: aaaa-mm-gg
	 * @return arraybidimensionale di dimensioni [lunghezza trenta_giorni_da_oggi][3]
	 */
	public String[][] getStatMensili(ArrayList<String> trentaGiorni) {
		// Nota: è necessario che esista per ogni giorno una riga,
		// se non sono presenti risultati per un giorno
		// porre una riga con valori numerici 0 nei conteggi

		String[][] res = new String[trentaGiorni.size()][];

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			con = DriverManager.getConnection(dburl, dbusr, dbpswd);
			for (int i = 0; i < trentaGiorni.size(); i++) {

				ArrayList<String> t = new ArrayList<String>();
				t.add(trentaGiorni.get(i));

				String query1 = "select count(*) from utente where dataisc='" + trentaGiorni.get(i) + "'";
				pstmt = con.prepareStatement(query1);
				rs = pstmt.executeQuery();

				if (rs.next()) // magari 0, ma comunque sempre vero
					t.add(rs.getString(1));

				String query2 = "select count(*) from prestito where datai='" + trentaGiorni.get(i) + "'";
				pstmt = con.prepareStatement(query2);
				rs = pstmt.executeQuery();

				if (rs.next()) // magari 0, ma comunque sempre vero
					t.add(rs.getString(1));
				res[i] = new String[3];
				res[i] = t.toArray(res[i]);

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

		return res;
	}

}
