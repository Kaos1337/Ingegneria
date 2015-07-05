package it.univr.is.entity;

import javax.servlet.http.HttpServletRequest;

/**
 * Bean per ricerca libri legati ad utente
 * @author marco
 *
 */
public class LibroUtente extends Libro {

	private String nome ;
	private String cognome ;
	private String citta ;
	private String provincia ;
	
	public LibroUtente(){
		super();
		nome = null ;
		cognome = null ;
		citta = null ;
		provincia = null ;
	}
	
	public LibroUtente(HttpServletRequest request) {
		this();
		setTitolo(request.getParameter("titolo"));
		setAutore(request.getParameter("autore") );
		setCategoria(request.getParameter("categoria"));
		setCategoria2(request.getParameter("categoria2"));
		setEdizione(request.getParameter("edizione"));
		setIsbn(request.getParameter("isbn"));
		setCopertina(request.getParameter("copertina"));
		setNome(request.getParameter("nome"));
		setCognome(request.getParameter("cognome"));
		setCitta(request.getParameter("citta"));
		setProvincia(request.getParameter("provincia"));
	}
	
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCitta() {
		return citta;
	}
	public void setCitta(String citta) {
		this.citta = citta;
	}
	public String getCognome() {
		return cognome;
	}
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	public String getProvincia() {
		return provincia;
	}
	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}
	
	
	
}
