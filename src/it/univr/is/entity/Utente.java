package it.univr.is.entity;

/*
 * Bean per elemento tabella Utente
 */

public class Utente {
	
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
