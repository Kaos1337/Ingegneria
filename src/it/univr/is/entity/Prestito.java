package it.univr.is.entity;

import javax.servlet.http.HttpServletRequest;

/**
 * Bean per elemento tabella Prestito
 * @author marco
 *
 */

public class Prestito extends Entity{
	
	private int id_libro;
	private String datai ;
	private String dataf ;
	
	public Prestito(){
		this.setId(-1);
		id_libro = -1 ;
		datai = null ;
		dataf = null ;
	
	}
	
	public Prestito(HttpServletRequest request) {
		// TODO Auto-generated constructor stub
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

	public int getId_libro() {
		return id_libro;
	}

	public void setId_libro(int id_libro) {
		this.id_libro = id_libro;
	}

}
