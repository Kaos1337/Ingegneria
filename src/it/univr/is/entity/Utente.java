package it.univr.is.entity;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Bean per elemento tabella Utente
 * @author marco
 *
 */
public class Utente extends Entity{
	
	private String email ;
	private String dataisc ;
	private String nome ;
	private String cognome ;
	private String password ;
	private String via ;
	private int civico ;
	private String cap ;
	private String citta ;
	private String provincia ;
	private int ruolo ;
	
	public Utente(){
		this.setId(-1);
		email = null ;
		dataisc = null ;
		nome = null ;
		cognome = null ;
		password = null ;
		via = null ;
		civico = -1 ;
		cap = null ;
		citta = null ;
		provincia = null ;
		ruolo = -1 ;
	}
	
	public Utente(HttpServletRequest request) {
		this();
		setNome(request.getParameter("nome"));
		setCognome(request.getParameter("cognome"));
		setEmail(request.getParameter("email"));
		setPassword(request.getParameter("password"));
		setVia(request.getParameter("via"));
		if(request.getParameter("civico")!=null) setCivico(Integer.parseInt(request.getParameter("civico")));
		setCap(request.getParameter("cap"));
		setCitta(request.getParameter("citta"));
		setProvincia(request.getParameter("provincia"));
	}

	public Utente(HttpSession session) {
		this();
		Utente u = (Utente) session.getAttribute("utente");
		
		setId(u.getId());
		email = u.getEmail() ;
		dataisc = u.getDataisc() ;
		nome = u.getNome() ;
		cognome = u.getCognome() ;
		password = u.getPassword() ;
		via = u.getVia() ;
		civico = u.getCivico() ;
		cap = u.getCap() ;
		citta = u.getCitta() ;
		provincia = u.getProvincia() ;
		ruolo = u.getRuolo() ;
	}

	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getVia() {
		return via;
	}

	public void setVia(String via) {
		this.via = via;
	}

	public String getCap() {
		return cap;
	}

	public void setCap(String cap) {
		this.cap = cap;
	}

	public int getCivico() {
		return civico;
	}

	public void setCivico(int civico) {
		this.civico = civico;
	}

	public String getCitta() {
		return citta;
	}

	public void setCitta(String citta) {
		this.citta = citta;
	}

	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public int getRuolo() {
		return ruolo;
	}

	public void setRuolo(int ruolo) {
		this.ruolo = ruolo;
	}

	public String getDataisc() {
		return dataisc;
	}

	public void setDataisc(String dataisc) {
		this.dataisc = dataisc;
	}

}
