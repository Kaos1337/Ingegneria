package it.univr.is.entity;

/*
 * Bean per elemento tabella Prestito
 */

public class Prestito {
	
	private String titolo ;
	private String utente ;
	private String datai ;
	private String dataf ;
	
	public Prestito(){
		
		titolo = null ;
		utente = null ;
		datai = null ;
		dataf = null ;
	
	}
	
	public String getTitolo() {
		return titolo;
	}
	
	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}
	
	public String getUtente() {
		return utente;
	}
	
	public void setUtente(String utente) {
		this.utente = utente;
	}

	public String getDatai() {
		return datai;
	}

	public void setDatai(String datai) {
		this.datai = datai;
	}

	public String getDataf() {
		return dataf;
	}

	public void setDataf(String dataf) {
		this.dataf = dataf;
	}

}
