package it.univr.is.entity;

import javax.servlet.http.HttpServletRequest;

/**
 * Bean per elemento tabella Prestito
 * @author marco
 *
 */

public class Prestito extends Entity{
	
	private String datai ;
	private String dataf ;
	
	public Prestito(){
		this.setId(-1);
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

}
