package it.univr.is.entity;

import javax.servlet.http.HttpServletRequest;

/**
 * Bean per elemento tabella Utente
 * @author marco
 *
 */
public class Utente implements Entity{
	
	private String email ;
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
		email = null ;
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
		setNome(request.getParameter("nome")!=null?request.getParameter("nome"):nome);
		setCognome(request.getParameter("cognome")!=null?request.getParameter("cognome"):cognome);
		setEmail(request.getParameter("email")!=null?request.getParameter("email"):email);
		setPassword(request.getParameter("password")!=null?request.getParameter("password"):password);
		setVia(request.getParameter("via")!=null?request.getParameter("via"):via);
		setCap(request.getParameter("cap")!=null?request.getParameter("cap"):cap);
		setCitta(request.getParameter("citta")!=null?request.getParameter("citta"):citta);
		setProvincia(request.getParameter("provincia")!=null?request.getParameter("provincia"):provincia);
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

}
