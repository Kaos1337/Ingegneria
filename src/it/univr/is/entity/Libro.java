package it.univr.is.entity;

import javax.servlet.http.HttpServletRequest;

/**
 * Bean per elemento tabella Libro
 * @author marco
 *
 */
public class Libro implements Entity{
	
	private int id ;
	private String titolo ;
	private int utente ;
	private String autore ;
	private String categoria ;
	private String categoria2 ;
	private int cancellato ;
	private String edizione ;
	private String isbn ;
	private String copertina ;
	
	public Libro(){
		id = -1;
		titolo = null;
		utente = -1;
		autore = null;
		categoria = null;
		categoria2 = null;
		cancellato = -1;
		edizione = null;
		isbn = null;
		copertina = null;
	}
	
	public Libro(HttpServletRequest request) {
		this();
		setTitolo(request.getParameter("titolo"));
		setAutore(request.getParameter("autore") );
		setCategoria(request.getParameter("categoria"));
		setCategoria2(request.getParameter("categoria2"));
		setEdizione(request.getParameter("edizione"));
		setIsbn(request.getParameter("isbn"));
		setCopertina(request.getParameter("copertina"));
	}

	public String getTitolo() {
		return titolo;
	}
	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}
	public int getUtente() {
		return utente;
	}
	public void setUtente(int utente) {
		this.utente = utente;
	}
	public String getAutore() {
		return autore;
	}
	public void setAutore(String autore) {
		this.autore = autore;
	}
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	public String getCategoria2() {
		return categoria2;
	}
	public void setCategoria2(String categoria2) {
		this.categoria2 = categoria2;
	}
	public int isCancellato() {
		return cancellato;
	}
	public void setCancellato(int cancellato) {
		this.cancellato = cancellato;
	}
	public String getEdizione() {
		return edizione;
	}
	public void setEdizione(String edizione) {
		this.edizione = edizione;
	}
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public String getCopertina() {
		return copertina;
	}
	public void setCopertina(String copertina) {
		this.copertina = copertina;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
